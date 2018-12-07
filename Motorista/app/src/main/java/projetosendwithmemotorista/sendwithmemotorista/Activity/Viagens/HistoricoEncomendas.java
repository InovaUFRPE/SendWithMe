package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import projetosendwithmemotorista.sendwithmemotorista.R;

public class HistoricoEncomendas extends AppCompatActivity {

    private Button btnvoltar;
    public ListView listaencomendas;
    private ArrayList<String> lista;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_encomendas);
        listaencomendas = findViewById(R.id.listaHistoricoEncomendas);
        setardadoslistaencomendas();

        btnvoltar = findViewById(R.id.Btnavanc);
        btnvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentvolta = new Intent(HistoricoEncomendas.this, TelaInicial.class);
                startActivity(intentvolta);
                finish();
            }
        });


    }

    private void setardadoslistaencomendas(){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("EncomendasSelecionadas");
        lista = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.infoviagens,R.id.textView8, lista);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    // Encomendas encomendas = ds.getValue(Encomendas.class);
                    lista.add(ds.getKey());
                    //setardadoslistaencomendas2();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
