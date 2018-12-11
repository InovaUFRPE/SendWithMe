package com.example.emano.sendwithme.ViagemPackage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.emano.sendwithme.MotoristaPackage.ListarMotoristasAdapter2;
import com.example.emano.sendwithme.MotoristaPackage.Motorista;
import com.example.emano.sendwithme.HomePackage.TelaInicial;
import com.example.emano.sendwithme.MotoristaPackage.ListarMotoristasAdapter2;
import com.example.emano.sendwithme.MotoristaPackage.Motorista;
import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.MotoristaPackage.InfoMotoristaViagem;
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


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent1 = new Intent(getApplicationContext(), InfoMotoristaViagem.class);

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

    public void voltarTelaInicial(View view){

        Intent intent = new Intent(ListarViagens.this, TelaInicial.class);
        startActivity(intent);

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
