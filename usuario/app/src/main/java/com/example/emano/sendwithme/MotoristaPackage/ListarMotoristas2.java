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

import com.example.emano.sendwithme.MotoristaPackage.ListarMotoristasAdapter2;
import com.example.emano.sendwithme.MotoristaPackage.Motorista;
import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.ViagemPackage.Viagem;
import com.example.emano.sendwithme.motoristaPackage.InfoMotoristaViagem;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListarMotoristas2 extends AppCompatActivity {

    private ArrayList<Motorista> motoristas = new ArrayList<>();
    private ArrayList<Motorista> listaMotoristaDef = new ArrayList<>();
    private ArrayList<Viagem> viagem = new ArrayList<>();
    private ArrayList<String> viagemIdMotorista = new ArrayList<>();
    private ArrayAdapter adapter;
    private ListView lista;
    private LatLng origemUsuario;
    private LatLng destinoUsuario;

    private DatabaseReference databaseReferenceMotorista;
    private DatabaseReference databaseReferenceViagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_motoristas2);
        setView();

        databaseReferenceMotorista = FirebaseDatabase.getInstance().getReference().child("usuario");
        databaseReferenceViagem = FirebaseDatabase.getInstance().getReference().child("Viagens");

        //criando lista de viagem (add o if depois)

        databaseReferenceViagem.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                viagem.clear();

                for (DataSnapshot dados:dataSnapshot.getChildren()){

                    Viagem viagem = dados.getValue(Viagem.class);
                    viagem.setViagemUID(dados.getKey());
                    addViagemNaLista(viagem);

                }
                carregarLista();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent1 = new Intent(getApplicationContext(), InfoMotoristaViagem.class);

                intent1.putExtra("nome", String.valueOf(listaMotoristaDef.get(position).getNome()));
                intent1.putExtra("sobrenome", String.valueOf(listaMotoristaDef.get(position).getSobrenome()));
                intent1.putExtra("email", String.valueOf(listaMotoristaDef.get(position).getEmail()));
                intent1.putExtra("sexo", String.valueOf(listaMotoristaDef.get(position).getSexo()));

                intent1.putExtra("viagemId", String.valueOf(viagem.get(position).getViagemUID()));
                intent1.putExtra("cidadedest", String.valueOf(viagem.get(position).getCidadedest()));
                intent1.putExtra("enderecodest", String.valueOf(viagem.get(position).getEndereçodest()));
                intent1.putExtra("endereco", String.valueOf(viagem.get(position).getEndereço()));
                intent1.putExtra("cidade", String.valueOf(viagem.get(position).getCidade()));
                intent1.putExtra("data", String.valueOf(viagem.get(position).getData()));
                intent1.putExtra("hora", String.valueOf(viagem.get(position).getHora()));
                intent1.putExtra("encomendas", String.valueOf(viagem.get(position).getEncomendas()));
                intent1.putExtra("data", String.valueOf(viagem.get(position).getData()));
                intent1.putExtra("hora", String.valueOf(viagem.get(position).getHora()));
                intent1.putExtra("usuarioid", String.valueOf(viagem.get(position).getUsuarioid()));

                startActivity(intent1);



            }
        });




    }

    public void carregarLista(){

        databaseReferenceMotorista.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                motoristas.clear();


                for(DataSnapshot dados:dataSnapshot.getChildren()){
                    Motorista motorista = dados.getValue(Motorista.class);
                    addMotoristaLista(motorista);


                }

                carregarListaDef();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }

    public void addViagemNaLista(Viagem viagem){
        this.viagem.add(viagem);
        this.viagemIdMotorista.add(viagem.getUsuarioid());
    }

    public void addMotoristaLista(Motorista motorista){
        this.motoristas.add(motorista);
    }


    public void carregarListaDef(){
        for(String idmotorista:viagemIdMotorista){
            for(Motorista motorista:motoristas){
                if(motorista.getId().equals(idmotorista)){
                    listaMotoristaDef.add(motorista);
                    break;
                }
            }


        }

        adapter.notifyDataSetChanged();




    }



    public void setView(){

        Intent intent = getIntent();
        String origem = intent.getStringExtra("origem");
        String destino = intent.getStringExtra("destino");
        ///String origem = "Recife";
        ///String destino = "Recife";

        origemUsuario = getLocationFromAddress(getApplicationContext(), origem);
        destinoUsuario = getLocationFromAddress(getApplicationContext(), destino);
        lista = findViewById(R.id.motoristas_view);
        adapter = new ListarMotoristasAdapter2(getApplicationContext(), listaMotoristaDef, viagem);
        lista.setAdapter(adapter);


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
