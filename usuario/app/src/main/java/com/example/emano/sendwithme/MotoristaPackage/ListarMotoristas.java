package com.example.emano.sendwithme.motoristaPackage;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.ViagemPackage.Viagem;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListarMotoristas extends AppCompatActivity {

    ArrayList<Motorista> motoristas = new ArrayList<>();
    ArrayAdapter adapter;
    ListView lista;
    FirebaseOptions options;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;
    LatLng latLng;
    LatLng latLng1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_motoristas);

        Intent intent = getIntent();
        String origem = intent.getStringExtra("origem");
        String destino = intent.getStringExtra("destino");
        latLng = getLocationFromAddress(ListarMotoristas.this, origem);
        latLng1 = getLocationFromAddress(ListarMotoristas.this, destino);

        criarMotoristaBanco();
        FirebaseApp bancoMotorista = FirebaseApp.initializeApp(getApplicationContext(), options, "appMotoristaBanco");
        final FirebaseDatabase motoristaBanco = FirebaseDatabase.getInstance(bancoMotorista);
        databaseReference = motoristaBanco.getReference("Viagens");


        lista = (ListView) findViewById(R.id.motoristas_view);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for(DataSnapshot dados: dataSnapshot.getChildren()){

                    Viagem viagem = dados.getValue(Viagem.class);

                    databaseReference2 = motoristaBanco.getReference("usuarios").child(viagem.getUsuarioid());

                    databaseReference2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Motorista motorista = dataSnapshot.getValue(Motorista.class);
                            addOnMotoristaOnLista(motorista);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    //Motorista motorista = dados.getValue(Motorista.class);

                    //addOnMotoristaOnLista(motorista);

                }

                setAdapter();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void criarMotoristaBanco() {

        String apiKeyMotorista = "AIzaSyC5XE_TNOJkzEf0ek4BXXCtO7zHP6qptWg";
        String appIdMotorista = "1:243051234707:android:6e217be4168ac264";
        String dataBaseUrlMotorista = "https://sendwithme-login-motorista.firebaseio.com";

        options = new FirebaseOptions.Builder()
                .setApiKey(apiKeyMotorista)
                .setApplicationId(appIdMotorista)
                .setDatabaseUrl(dataBaseUrlMotorista)
                .build();


    }

    private void addOnMotoristaOnLista(Motorista motorista){
        this.motoristas.add(motorista);

    }

    private void setAdapter(){
        adapter = new ListarMotoristasAdapter(getApplicationContext(), this.motoristas);
        lista.setAdapter(adapter);
    }

    private ArrayList<Motorista>  filtrarLista(ArrayList<Motorista> listaMotorista){
        ArrayList<Motorista> listaFiltrada = new ArrayList<>();

        for (Motorista motorista:listaMotorista) {
            //condições de chacagem pra add em listaFiltrada
            //if(SphericalUtil.computeDistanceBetween(latLng, latLng1) < 70000){
            ///add latLng e latLng1 como as posições a serem comparadas

        }


        return listaFiltrada;

    }

    public LatLng getLocationFromAddress(Context context, String inputtedAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng resLatLng = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(inputtedAddress, 5);
            if (address == null) {
                return null;
            }

            if (address.size() == 0) {
                return null;
            }

            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            resLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (IOException ex) {

            ex.printStackTrace();
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return resLatLng;
    }

}
