package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.UUID;

import projetosendwithmemotorista.sendwithmemotorista.Activity.TelaPerfil.TelaPerfil;
import projetosendwithmemotorista.sendwithmemotorista.Helper.Base64Custom;
import projetosendwithmemotorista.sendwithmemotorista.Helper.PreferenciasAndroid;
import projetosendwithmemotorista.sendwithmemotorista.R;

public class Viagemrevisao extends AppCompatActivity {

    private String cidadedest;
    private String endereçodest;
    private String cidade;
    private String endereço;
    private String data;
    private String hora;
    private String encomendas;
    private Integer assentos;
    private String usuarioid;

    private String qtassentos;

    private String PARTIDA;
    private String DESTINO;
    private String DATAHORA;


    private TextView txtpartida;
    private TextView txtdestino;
    private TextView txtdatapartida;
    private TextView txtassentos;
    private TextView txtencomendas;

    private Button btnvoltar;
    private Button btnsalvarviagem;

    private AlertDialog caixa;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagem_revisao);

        //
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
            usuarioid = extras.getString("usuarioid");

            qtassentos = assentos.toString();

            //


        }

        //
        PARTIDA = endereço + " , " + cidade + ".";
        DESTINO = endereçodest + " , " + cidadedest + ".";
        DATAHORA = data + "  as  " + hora;


        //
        txtpartida = findViewById(R.id.Setpartida);
        txtdestino = findViewById(R.id.Setdestino);
        txtdatapartida = findViewById(R.id.Setdate);
        txtassentos = findViewById(R.id.Setassent);
        txtencomendas = findViewById(R.id.Setencom);

        //

        btnsalvarviagem = findViewById(R.id.Btnsalvar);
        btnvoltar = findViewById(R.id.Btnvolt);
        //
        funcsetainfotela();

        //
        iniciafirebase();

        //
        btnsalvarviagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrecaixasalvar();
            }
        });

        //
        btnvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funcvoltar();
            }
        });

    }


    //
    private void iniciafirebase() {
        FirebaseApp.initializeApp(Viagemrevisao.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    //
    private void abrecaixasalvar() {

        final PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(Viagemrevisao.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tem certeza que deseja salvar essa viagem?");
        builder.setMessage("mimimi");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //
                ViagemFB v = new ViagemFB();
                v.setViagemUID(UUID.randomUUID().toString());
                v.setCidade(cidade);
                v.setEndereço(endereço);
                v.setCidadedest(cidadedest);
                v.setEndereçodest(endereçodest);
                v.setData(data);
                v.setHora(hora);
                v.setEncomendas(encomendas);
                v.setAssentos(assentos);
                v.setUsuarioid(preferenciasAndroid.getIdentificador());
                String idAleatorio = Base64Custom.codificarBase64(v.getViagemUID());
                databaseReference.child("Viagens").child(idAleatorio).setValue(v);

            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //
            }
        });
        caixa = builder.create();
        caixa.show();
    }




    //
    private void funcvoltar() {
        Intent i = new Intent(Viagemrevisao.this, Viagemconfig.class);
        i.putExtra("cidade", cidade);
        i.putExtra("endereço",endereço);
        i.putExtra("data",data);
        i.putExtra("hora",hora);
        i.putExtra("cidadedest",cidadedest);
        i.putExtra("endereçodest",endereçodest);
        i.putExtra("assentos",assentos);
        i.putExtra("encomendas",encomendas);
        i.putExtra("usuarioid",usuarioid);
        startActivity(i);
    }

    //
    private void funcsetainfotela() {
        txtpartida.setText(PARTIDA);
        txtdestino.setText(DESTINO);
        txtdatapartida.setText(DATAHORA);
        txtassentos.setText(qtassentos);
        if(encomendas.equals("Y")){
            txtencomendas.setText("Sim!");
        }
        else{
            txtencomendas.setText("Não");
        }
    }
}
