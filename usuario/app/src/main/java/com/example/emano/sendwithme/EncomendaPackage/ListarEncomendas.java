package com.example.emano.sendwithme.EncomendaPackage;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ListarEncomendas extends AppCompatActivity {

    private ArrayList<Encomenda> encomendas = new ArrayList<>();
    private ArrayAdapter adapter;
    private ListView lista;
    private DatabaseReference databaseReference;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_encomendas);

        databaseReference = FirebaseDatabase.getInstance().getReference("Encomendas").child(user.getUid());

        lista = (ListView) findViewById(R.id.encomenda_view);
        setAdapter();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                encomendas.clear();

                for(DataSnapshot dados: dataSnapshot.getChildren()){

                    Encomenda encomenda = dados.getValue(Encomenda.class);
                    addOnEncomendaOnLista(encomenda);

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void addOnEncomendaOnLista(Encomenda encomenda){
        this.encomendas.add(encomenda);
    }

    private void setAdapter(){
        adapter = new ListarEncomendasAdapter(getApplicationContext(), this.encomendas);
        lista.setAdapter(adapter);
    }


}
