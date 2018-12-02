package com.example.emano.sendwithme.ChatPackage;

import com.example.emano.sendwithme.MotoristaPackage.ListarMotoristasAdapter;
import com.example.emano.sendwithme.MotoristaPackage.Motorista;
import com.example.emano.sendwithme.MotoristaPackage.ListarMotoristasAdapter;
import com.example.emano.sendwithme.MotoristaPackage.Motorista;
import com.example.emano.sendwithme.R;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaChatActivity extends AppCompatActivity {

    private ArrayList<Motorista> motoristas = new ArrayList<>();
    private ArrayList<String> minhasConversas = new ArrayList<>();
    private ArrayAdapter adapter;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceConversa;
    private ListView listaUsuario;
    ///USE ISSO AO INVES DA STRING ESTATICA
    //private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_chat);


        final String idPassageiro = "VhEBDyqKOmhvK0sDh4rf15ddghn2";

        listaUsuario = findViewById(R.id.listaUsuariosId);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("usuario");
        databaseReferenceConversa = FirebaseDatabase.getInstance().getReference().child("mensagens").child(idPassageiro);


        databaseReferenceConversa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                minhasConversas.clear();

                for(DataSnapshot dados:dataSnapshot.getChildren()){

                    //Map<String, Object> map = (HashMap<String, Object>) dataSnapshot.getValue();

                    //String a = dados.getKey();

                    //int b = 4;

                    String valor = dados.getKey();
                    addMotoristaIdLista(valor);
                }

                carregarLista();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        listaUsuario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Motorista motorista = motoristas.get(position);
                if(motorista.getId().equals(idPassageiro)){
                    Toast.makeText(getApplicationContext(), "É vc bro", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(ListaChatActivity.this, ChatActivity.class);

                    // recupera dados a serem passados


                    // enviando dados para conversa activity


                    // recupera dados a serem passados


                    // enviando dados para conversa activity
                    intent.putExtra("nome", motorista.getNome() );
                    intent.putExtra("id", motorista.getId() );
                    intent.putExtra("idcurrentuser", idPassageiro);

                    startActivity(intent);

                    //abrir as instancias de conversa like udemy -- se exploda
                    /*
                    MANDA PRA TELA DE CONVERSAS
                    CRIA O OBJETO CONVERSA
                    JOGA NO BANCO
                    BEE HAPPY
                     */




                }
            }
        });



    }

    private void carregarLista() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                motoristas.clear();

                for(DataSnapshot dados:dataSnapshot.getChildren()){

                    Motorista motorista = dados.getValue(Motorista.class);
                    if(minhasConversas.contains(motorista.getId())){
                        addMotoristaLista(motorista);
                    }




                }

                setAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void addMotoristaLista(Motorista motorista) {
        this.motoristas.add(motorista);
    }

    private void addMotoristaIdLista(String cpf) {
        this.minhasConversas.add(cpf);
    }

    private void setAdapter() {
        adapter = new ListarMotoristasAdapter(getApplicationContext(), this.motoristas);
        listaUsuario.setAdapter(adapter);
    }


}