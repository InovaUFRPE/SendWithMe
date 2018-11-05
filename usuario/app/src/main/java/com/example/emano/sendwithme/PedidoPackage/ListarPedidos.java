package com.example.emano.sendwithme.PedidoPackage;

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

public class ListarPedidos extends AppCompatActivity {

    DatabaseReference databaseReference;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    ArrayList<Pedido> pedidos;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pedidos);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Pedidos");

        lista = (ListView) findViewById(R.id.lista_pedidos_view);
        final ArrayAdapter adapter = new ListarPedidosAdapter(ListarPedidos.this, pedidos);
        lista.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dados: dataSnapshot.getChildren()){

                    Pedido pedido = dados.getValue(Pedido.class);
                    pedidos.add(pedido);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    

    
}
