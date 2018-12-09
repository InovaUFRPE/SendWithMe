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

    private ArrayList<EncomendaSelecionada> encomendaSelecionadas = new ArrayList<>();
    private ArrayAdapter adapter;
    private ListView lista;
    private DatabaseReference databaseReference;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private ArrayList<String> idEncomendaSelecionadas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_encomendas_selecionadas2);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("EncomendasSelecionadas");
        lista = (ListView) findViewById(R.id.encomenda_selecionada_view);
        setAdapter();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                encomendaSelecionadas.clear();

                for(DataSnapshot dados: dataSnapshot.getChildren()){

                    EncomendaSelecionada encomendaSelecionada = dados.getValue(EncomendaSelecionada.class);
                    idEncomendaSelecionadas.add(dados.getKey());

                    if(encomendaSelecionada.getIdUsuario().equals(user.getUid()) && !encomendaSelecionada.getStatus().equals("Finalizada")) {

                        addOnEncomendaSelecionadaOnLista(encomendaSelecionada);

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
                intent.putExtra("nomeObjeto", String.valueOf(encomendaSelecionadas.get(position).getNomeObjeto()));
                intent.putExtra("origem", String.valueOf(encomendaSelecionadas.get(position).getCidadeOri()));
                intent.putExtra("destino", String.valueOf(encomendaSelecionadas.get(position).getCidadeDest()));
                intent.putExtra("idUsuario", String.valueOf(encomendaSelecionadas.get(position).getIdUsuario()));
                intent.putExtra("idMotorista", String.valueOf(encomendaSelecionadas.get(position).getIdMotorista()));
                intent.putExtra("idEncomenda", String.valueOf(encomendaSelecionadas.get(position).getIdEncomenda()));
                intent.putExtra("descricao", String.valueOf(encomendaSelecionadas.get(position).getDescricao()));
                intent.putExtra("hora", String.valueOf(encomendaSelecionadas.get(position).getHoraViagem()));
                intent.putExtra("data", String.valueOf(encomendaSelecionadas.get(position).getDataViagem()));
                intent.putExtra("idEncomendaSelecionada", String.valueOf(idEncomendaSelecionadas.get(position)));
                startActivity(intent);

            }
        });

    }

    private void setAdapter(){

        adapter = new ListarEncomendasSelecionadasAdapter(getApplicationContext(), this.encomendaSelecionadas);
        lista.setAdapter(adapter);

    }

    private void addOnEncomendaSelecionadaOnLista(EncomendaSelecionada encomendaSelecionada){

        this.encomendaSelecionadas.add(encomendaSelecionada);

    }

}
