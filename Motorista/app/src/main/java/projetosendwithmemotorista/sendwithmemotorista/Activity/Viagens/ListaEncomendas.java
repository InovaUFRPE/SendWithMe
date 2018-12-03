package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import projetosendwithmemotorista.sendwithmemotorista.Helper.PreferenciasAndroid;
import projetosendwithmemotorista.sendwithmemotorista.R;

public class ListaEncomendas extends AppCompatActivity {

    private Button btnavancar;
    private String cidadedest;
    private String endereçodest;
    private String cidade;
    private String endereço;
    private String data;
    private String hora;
    private String encomendas;

    private Integer assentos;

    private TextView encomendainfo;

    private EditText edtassdisp;

    private CheckBox encomendabox;
    private ListView listaencomendas;
    private ArrayList<String> lista;
    private ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_encomendas);
        btnavancar = findViewById(R.id.btnavancar);
        listaencomendas = findViewById(R.id.listaviagens);
        setardadoslistaencomendas();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cidade = extras.getString("cidade");
            endereço = extras.getString("endereço");
            data = extras.getString("data");
            hora = extras.getString("hora");
            cidadedest = extras.getString("cidadedest");
            endereçodest = extras.getString("endereçodest");
            assentos = extras.getInt("assentos");
            encomendas = extras.getString("encomendas");
        }

        btnavancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funcacanvar();
            }

        });

    }

    private void setardadoslistaencomendas(){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Encomendas");
        lista = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.infoviagens,R.id.textView8, lista);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    Encomendas encomendas = ds.getValue(Encomendas.class);
                   // String userId = String.valueOf(encomendas.getUsuarioid());
                   // final PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(ListaEncomendas.this);
                   // if (userId.equals(preferenciasAndroid.getIdentificador()))
                    lista.add(" Cidade Origem: " + encomendas.getCidadeOrigem() +"\n" + " Cidade Destino: "+ encomendas.getCidadeDestino());
                    // lista.add(ds.getValue().toString());

                }

                listaencomendas.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void funcacanvar() {


                Intent i = new Intent(ListaEncomendas.this, Viagemrevisao.class);
                i.putExtra("cidade", cidade);
                i.putExtra("endereço", endereço);
                i.putExtra("data", data);
                i.putExtra("hora", hora);
                i.putExtra("cidadedest", cidadedest);
                i.putExtra("endereçodest", endereçodest);
                i.putExtra("assentos", assentos);
                i.putExtra("encomendas", encomendas);
                startActivity(i);
            }



}
