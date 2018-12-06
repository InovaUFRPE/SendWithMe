package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import projetosendwithmemotorista.sendwithmemotorista.R;

public class Viagemconfig extends AppCompatActivity {

    private String cidadedest;
    private String endereçodest;
    private String cidade;
    private String endereço;
    private String data;
    private String hora;
    private String encomendas;
    private String Qassentos;

    private Integer assentos;

    private TextView encomendainfo;

    private EditText edtassdisp;

    private CheckBox encomendabox;

    private Button btnavançar;
    private Button btnvoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagem_config);

        //
        encomendainfo = findViewById(R.id.Txtbtnenc);

        //
        edtassdisp = findViewById(R.id.Edtassdisp);
        encomendabox = findViewById(R.id.Encomendabox);

        //
        btnavançar = findViewById(R.id.Btnavanc);
        btnvoltar = findViewById(R.id.Btnvolt);

        //

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

            Qassentos = assentos.toString();
        }

        edtassdisp.setText(Qassentos);

        btnavançar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funcacanvar();
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
    private void funcvoltar() {
        Intent i = new Intent(Viagemconfig.this, DestinoViagem.class);
        i.putExtra("cidade", cidade);
        i.putExtra("endereço",endereço);
        i.putExtra("data",data);
        i.putExtra("hora",hora);
        i.putExtra("cidadedest",cidadedest);
        i.putExtra("endereçodest",endereçodest);
        startActivity(i);
    }

    //
    private void funcacanvar() {

        assentos = Integer.parseInt(edtassdisp.getText().toString());

        //
        if(assentos.equals("")){
            Toast.makeText(this,"Preencha todos campos!",Toast.LENGTH_SHORT).show();
        }
        else{
            if (encomendabox.isChecked()){
                encomendas = "Y";
                Intent i = new Intent(Viagemconfig.this, ListaEncomendas.class);
                i.putExtra("cidade", cidade);
                i.putExtra("endereço",endereço);
                i.putExtra("data",data);
                i.putExtra("hora",hora);
                i.putExtra("cidadedest",cidadedest);
                i.putExtra("endereçodest",endereçodest);
                i.putExtra("assentos",assentos);
                i.putExtra("encomendas",encomendas);
                startActivity(i);
            }
            else {
                encomendas = "N";
                Intent i = new Intent(Viagemconfig.this, Viagemrevisao.class);
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

    }

}
