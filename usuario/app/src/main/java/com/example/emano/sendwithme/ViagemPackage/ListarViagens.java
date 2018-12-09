package com.example.emano.sendwithme.ViagemPackage;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.emano.sendwithme.MotoristaPackage.ListarMotoristasAdapter2;
import com.example.emano.sendwithme.MotoristaPackage.Motorista;
import com.example.emano.sendwithme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListarViagens extends AppCompatActivity {



    private ArrayList<Motorista> motoristas = new ArrayList<>();
    private ArrayList<Viagem> viagens = new ArrayList<>();
    private ArrayList<String> viagemIdUsuario = new ArrayList<>();
    private ArrayList<String> viagemIdMotorista = new ArrayList<>();
    private ArrayAdapter adapter;
    private ListView lista;

    private DatabaseReference databaseReferenceMotorista;
    private DatabaseReference databaseReferenceViagens;
    private DatabaseReference databaseReferenceUsuarioViagem;

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_viagens);

        setView();
        databaseReferenceUsuarioViagem = FirebaseDatabase.getInstance().getReference().child("UsuarioViagem").child(user.getUid());

        databaseReferenceViagens = FirebaseDatabase.getInstance().getReference().child("Viagens");

        databaseReferenceMotorista = FirebaseDatabase.getInstance().getReference().child("usuario");

        databaseReferenceUsuarioViagem.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                viagemIdUsuario.clear();

                for (DataSnapshot dados:dataSnapshot.getChildren()){

                    addIdListaViagem(dados.getKey());

                }
                carregarListaViagens();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void carregarListaViagens() {

        databaseReferenceViagens.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                viagens.clear();
                viagemIdMotorista.clear();

                for(DataSnapshot dados:dataSnapshot.getChildren()){
                    Viagem viagem = dados.getValue(Viagem.class);
                    if(viagem != null && viagemIdUsuario.contains(dados.getKey())){
                        viagem.setViagemUID(dados.getKey());
                        viagens.add(viagem);
                        viagemIdMotorista.add(viagem.getUsuarioid());

                    }

                }
                addMotoristaLista();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void addMotoristaLista() {

        databaseReferenceMotorista.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                motoristas.clear();

                for(DataSnapshot dados:dataSnapshot.getChildren()){
                    Motorista motorista = dados.getValue(Motorista.class);
                    if(motorista != null && viagemIdMotorista.contains(motorista.getId())){
                        motoristas.add(motorista);

                    }

                }
                adapter.notifyDataSetChanged();






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    private void addIdListaViagem(String key) {
        this.viagemIdUsuario.add(key);


    }

    private void setView() {
        lista = findViewById(R.id.motoristas_view);
        adapter = new ListarMotoristasAdapter2(getApplicationContext(), motoristas, viagens);
        lista.setAdapter(adapter);

    }
}
