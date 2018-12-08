package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import projetosendwithmemotorista.sendwithmemotorista.Activity.CadastroMotorista.CadastroActivity;
import projetosendwithmemotorista.sendwithmemotorista.Helper.PreferenciasAndroid;
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
        setardadoslistaencomendas();
        listaencomendas = findViewById(R.id.listaHistoricoEncomendas);

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
        adapter = new ArrayAdapter<String>(this,R.layout.infoencomendas,R.id.textViewHistEncomendas, lista);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    EncomendasSelecionadas encomendasSelecionadas = ds.getValue(EncomendasSelecionadas.class);
                    String userId = String.valueOf(encomendasSelecionadas.getIdMotorista());
                    final PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(HistoricoEncomendas.this);
                    String idMotoristaLogado = preferenciasAndroid.getIdentificador();
                    if (userId.equals(idMotoristaLogado)) {
                        lista.add(" Cidade de Origem da viagem :" + encomendasSelecionadas.getCidadeOrigemViagem() +"\n" +"Cidade Origem da encomenda: "+encomendasSelecionadas.getCidadeOri() + "\n" + " Cidade Destino da encomenda: "+ encomendasSelecionadas.getCidadeDest() + "\n"+ " Data da viagem: "+encomendasSelecionadas.getDataViagem() + "\n" + " Hora da viagem: "+encomendasSelecionadas.getHoraViagem());
                        //setardadoslistaencomendas2();
                     //   Toast.makeText(HistoricoEncomendas.this, "TO NO IF" + userId + "logado"+ idMotoristaLogado, Toast.LENGTH_LONG).show();
                        listaencomendas.setAdapter(adapter);
                    } else {

                      //  Toast.makeText(HistoricoEncomendas.this, "NAOOOOO", Toast.LENGTH_LONG).show();

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
