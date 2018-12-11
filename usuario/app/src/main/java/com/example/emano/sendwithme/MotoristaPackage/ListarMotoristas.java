package com.example.emano.sendwithme.MotoristaPackage;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.emano.sendwithme.EncomendaPackage.ListarEncomendas;
import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.ViagemPackage.Viagem;
import com.example.emano.sendwithme.caronaPackage.EnderecoCarona;
import com.example.emano.sendwithme.MotoristaPackage.InfoMotoristaViagem;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.SphericalUtil;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListarMotoristas extends AppCompatActivity {

    ArrayList<Motorista> motoristas = new ArrayList<>();
    ArrayList<Viagem> viagens = new ArrayList<>();
    ArrayAdapter adapter;
    ListView lista;
    FirebaseOptions options;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;
    LatLng latLng;
    LatLng latLngDestinoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_motoristas);

        Intent intent = getIntent();
        String origem = intent.getStringExtra("origem");
        String destino = intent.getStringExtra("destino");
        //String origem = "Recife";
        //String destino = "Recife";
        latLng = getLocationFromAddress(ListarMotoristas.this, origem);
        latLngDestinoUsuario = getLocationFromAddress(ListarMotoristas.this, destino);

        //FirebaseApp bancoMotorista = FirebaseApp.initializeApp(getApplicationContext(), options, "appMotoristaBanco");
        //final FirebaseDatabase motoristaBanco = FirebaseDatabase.getInstance(bancoMotorista);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("usuario");
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Viagens");


        lista = (ListView) findViewById(R.id.motoristas_view);

        //pega todas as viagens q atendem ao requisito

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                motoristas.clear();
                viagens.clear();


                for(DataSnapshot dados: dataSnapshot.getChildren()){


                    Viagem viagem = dados.getValue(Viagem.class);
                    viagem.setViagemUID(dados.getKey());


                    LatLng latLngDestinoViagem = getLocationFromAddress(ListarMotoristas.this, viagem.getCidadedest());

                    if(SphericalUtil.computeDistanceBetween(latLngDestinoUsuario, latLngDestinoViagem) < 60000){
                        //addMotoristaListaById(viagem.getUsuarioid());
                        addOnViagemOnLista(viagem);

                        final String idUsuario = viagem.getUsuarioid();
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for(DataSnapshot dados: dataSnapshot.getChildren()) {

                                    Motorista motorista = dados.getValue(Motorista.class);

                                    if (motorista.getId().equals(idUsuario)){
                                        addOnMotoristaOnLista(motorista);
                                    }

                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    //addOnMotoristaOnLista(motorista);

                }
                //deois do for
                //incrementarListaMotorista(viagens);

                ///depis do for
                // setAdapter();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*for (final Viagem viagem : viagens){

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot dados: dataSnapshot.getChildren()) {

                        Motorista motorista = dados.getValue(Motorista.class);

                        if (motorista.getId().equals(viagem.getUsuarioid())){
                            addOnMotoristaOnLista(motorista);
                        }

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }*/

        if(viagens.size() == 0){
            Toast.makeText(ListarMotoristas.this, "oka", Toast.LENGTH_SHORT).show();
        }

        setAdapter();




        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent1 = new Intent(ListarMotoristas.this, InfoMotoristaViagem.class);

                intent1.putExtra("nome", String.valueOf(motoristas.get(position).getNome()));
                intent1.putExtra("sobrenome", String.valueOf(motoristas.get(position).getSobrenome()));
                intent1.putExtra("email", String.valueOf(motoristas.get(position).getEmail()));
                intent1.putExtra("sexo", String.valueOf(motoristas.get(position).getSexo()));

                intent1.putExtra("viagemId", String.valueOf(viagens.get(position).getViagemUID()));
                intent1.putExtra("cidadedest", String.valueOf(viagens.get(position).getCidadedest()));
                intent1.putExtra("enderecodest", String.valueOf(viagens.get(position).getEndereçodest()));
                intent1.putExtra("endereco", String.valueOf(viagens.get(position).getEndereço()));
                intent1.putExtra("cidade", String.valueOf(viagens.get(position).getCidade()));
                intent1.putExtra("data", String.valueOf(viagens.get(position).getData()));
                intent1.putExtra("hora", String.valueOf(viagens.get(position).getHora()));
                intent1.putExtra("encomendas", String.valueOf(viagens.get(position).getEncomendas()));
                intent1.putExtra("data", String.valueOf(viagens.get(position).getData()));
                intent1.putExtra("hora", String.valueOf(viagens.get(position).getHora()));
                intent1.putExtra("usuarioid", String.valueOf(viagens.get(position).getUsuarioid()));

                startActivity(intent1);

            }
        });

    }

    private void incrementarListaMotorista(ArrayList<Viagem> viagens){

        for(Viagem viagem: viagens){
            addMotoristaListaById(viagem.getUsuarioid());

        }

    }

    private void addOnMotoristaOnLista(Motorista motorista){
        this.motoristas.add(motorista);

    }


    private void addMotoristaListaById(final String id){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dados: dataSnapshot.getChildren()) {

                    Motorista motorista = dados.getValue(Motorista.class);

                    if (motorista.getId().equals(id)){
                        addOnMotoristaOnLista(motorista);
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    private void addOnViagemOnLista(Viagem viagem){
        this.viagens.add(viagem);

    }

    private void setAdapter(){
        adapter = new ListarMotoristasAdapter(getApplicationContext(), this.motoristas);
        adapter.notifyDataSetChanged();
        lista.setAdapter(adapter);
    }

    private ArrayList<Motorista>  filtrarLista(ArrayList<Motorista> listaMotorista){
        ArrayList<Motorista> listaFiltrada = new ArrayList<>();

        for (Motorista motorista:listaMotorista) {
            //condições de chacagem pra add em listaFiltrada
            //if(SphericalUtil.computeDistanceBetween(latLng, latLngDestinoUsuario) < 70000){
            ///add latLng e latLngDestinoUsuario como as posições a serem comparadas

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
