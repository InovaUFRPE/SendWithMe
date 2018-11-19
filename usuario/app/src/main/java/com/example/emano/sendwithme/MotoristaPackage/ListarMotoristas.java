package com.example.emano.sendwithme.MotoristaPackage;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.emano.sendwithme.MotoristaPackage.Motorista;
import com.example.emano.sendwithme.PedidoPackage.Pedido;
import com.example.emano.sendwithme.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;

public class ListarMotoristas extends AppCompatActivity {

    ArrayList<Motorista> motoristas = new ArrayList<>();
    ArrayAdapter adapter;
    ListView lista;
    FirebaseOptions options;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_motoristas);

        criarMotoristaBanco();
        FirebaseApp bancoMotorista = FirebaseApp.initializeApp(getApplicationContext(), options, "appMotoristaBanco");
        FirebaseDatabase motoristaBanco = FirebaseDatabase.getInstance(bancoMotorista);
        databaseReference = motoristaBanco.getReference("usuario");


        lista = (ListView) findViewById(R.id.motoristas_view);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for(DataSnapshot dados: dataSnapshot.getChildren()){

                    Motorista motorista = dados.getValue(Motorista.class);

                    addOnMotoristaOnLista(motorista);

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
}
