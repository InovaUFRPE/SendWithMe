package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import projetosendwithmemotorista.sendwithmemotorista.Activity.TelaPerfil.TelaPerfil;
import projetosendwithmemotorista.sendwithmemotorista.Helper.PreferenciasAndroid;
import projetosendwithmemotorista.sendwithmemotorista.R;

public class ListaViagens extends AppCompatActivity {

    private TextView edtcidade;
    private TextView edtendereço;
    private TextView edtdata;
    private TextView edthora;
    private Button btnvoltar;
    private ListView listaviagens;
    private ArrayList<String> lista;
    private ArrayAdapter<String> adapter;
    private String idviagemselect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_viagens);

        setardadoslistaviagem();
        listaviagens = findViewById(R.id.listaviagens);
        btnvoltar = findViewById(R.id.Btnavanc);
        btnvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentvolta = new Intent(ListaViagens.this, TelaInicial.class);
                startActivity(intentvolta);
                ListaViagens.this.finish();
            }
        });

        listaviagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idviagemselect = lista.get(position);
                Intent i = new Intent(ListaViagens.this, StatusViagem.class);
                i.putExtra("idviagem",idviagemselect);
                startActivity(i);

            }
        });
    }

    private void setardadoslistaviagem(){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Viagens");
        lista = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.infoviagens,R.id.textView8, lista);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    ViagemFB viagem = ds.getValue(ViagemFB.class);
                    String userId = String.valueOf(viagem.getUsuarioid());
                    final PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(ListaViagens.this);
                    if (userId.equals(preferenciasAndroid.getIdentificador()))
                        lista.add(" Cidade Origem: " + viagem.getCidade() +"\n" + " Cidade Destino: "+ viagem.getCidadedest() + "\n"+ " Data:"+viagem.getData() + "\n" + " Hora: "+viagem.getHora() + "\n"+ "Assento(s): " +viagem.getAssentos() + "\n" + "Encomenda(s): " + viagem.getEncomendas() );
                       // lista.add(ds.getValue().toString());

                }

                listaviagens.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setView(){
        edtcidade = findViewById(R.id.Edtcity);
        edtendereço = findViewById(R.id.Edtend);
      //  edtdata = findViewById(R.id.Edtdata);
      //  edthora = findViewById(R.id.Edthora);
    }
}
