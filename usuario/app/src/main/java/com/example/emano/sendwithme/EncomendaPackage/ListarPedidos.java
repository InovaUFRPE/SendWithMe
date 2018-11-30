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

public class ListarPedidos extends AppCompatActivity {

    DatabaseReference databaseReference;
    ArrayList<Pedido> pedidos = new ArrayList<>();
    ArrayAdapter adapter;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pedidos);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Pedidos").child(user.getUid());

        lista = (ListView) findViewById(R.id.lista_pedidos_view);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot dados: dataSnapshot.getChildren()){

                    Pedido dado = dados.getValue(Pedido.class);

                    addOnLista(dado);

                }

                setaAdapter();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),InfoPedido.class);
                intent.putExtra("origem",String.valueOf(pedidos.get(position).getOrigem()));
                intent.putExtra("destino",String.valueOf(pedidos.get(position).getDestino()));
                intent.putExtra("titulo",String.valueOf(pedidos.get(position).getTitulo()));
                intent.putExtra("nome",String.valueOf(pedidos.get(position).getObjeto()));
                startActivity(intent);

            }
        });*/

    }

    public void addOnLista(Pedido pedido){

        this.pedidos.add(pedido);

    }

    public void setaAdapter(){

        //adapter = new ListarPedidosAdapter(getApplicationContext(),this.pedidos);
        //lista.setAdapter(adapter);

    }

    
}
