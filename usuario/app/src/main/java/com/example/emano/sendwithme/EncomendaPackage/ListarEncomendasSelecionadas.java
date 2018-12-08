package com.example.emano.sendwithme.EncomendaPackage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.emano.sendwithme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListarEncomendasSelecionadas extends AppCompatActivity {

    private ArrayList<EncomendaSelecionada> encomendas = new ArrayList<>();
    private ArrayAdapter adapter;
    private ListView lista;
    private DatabaseReference databaseReference;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_encomendas_selecionadas);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("EncomendasSelecionadas");

        lista = (ListView) findViewById(R.id.encomendas_selecionadas_view);
        setAdapter();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                encomendas.clear();

                for(DataSnapshot dados: dataSnapshot.getChildren()){

                    EncomendaSelecionada encomenda = dados.getValue(EncomendaSelecionada.class);

                    if(encomenda.getIdUsuario() == user.getUid()) {
                        addOnEncomendaOnLista(encomenda);
                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(ListarEncomendasSelecionadas.this, InfoEncomendaSelecionada.class);
                intent.putExtra("nomeObjeto", String.valueOf(encomendas.get(position).getNomeObjeto()));
                intent.putExtra("origem", String.valueOf(encomendas.get(position).getCidadeOri()));
                intent.putExtra("destino", String.valueOf(encomendas.get(position).getCidadeDest()));
                intent.putExtra("descricao", String.valueOf(encomendas.get(position).getDescricao()));
                intent.putExtra("data", String.valueOf(encomendas.get(position).getDataViagem()));
                intent.putExtra("hora", String.valueOf(encomendas.get(position).getHoraViagem()));
                intent.putExtra("idMotorista", String.valueOf(encomendas.get(position).getIdMotorista()));

                startActivity(intent);

            }
        });


    }

    private void addOnEncomendaOnLista(EncomendaSelecionada encomenda){
        this.encomendas.add(encomenda);
    }

    private void setAdapter(){
        adapter = new ListarEncomendasSelecionadasAdapter(getApplicationContext(), this.encomendas);
        lista.setAdapter(adapter);
    }



}
