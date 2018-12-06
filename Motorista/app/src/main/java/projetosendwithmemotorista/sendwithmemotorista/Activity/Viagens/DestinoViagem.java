package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import projetosendwithmemotorista.sendwithmemotorista.R;

public class DestinoViagem extends AppCompatActivity {

    private String cidadedest;
    private String endereçodest;
    private String cidade;
    private String endereço;
    private String data;
    private String hora;
    private String encomendas;
    private Integer assentos;

    private EditText edtcidadedest;
    private EditText edtendereçodest;

    private Button btnavançar;
    private Button btnvoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destino_viagem);

        //
        edtcidadedest = findViewById(R.id.Edtcity);
        edtendereçodest = findViewById(R.id.Edtend);

        btnavançar = findViewById(R.id.Btnavanc);
        btnvoltar = findViewById(R.id.Btnvolt);

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
        }

        edtcidadedest.setText(cidadedest);
        edtendereçodest.setText(endereçodest);

        //
        btnavançar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funcavanc();
            }
        });

        btnvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funcvoltar();
            }
        });

    }

    //
    private void funcvoltar() {
        Intent i = new Intent(DestinoViagem.this, InicioViagem.class);
        i.putExtra("cidade", cidade);
        i.putExtra("endereço",endereço);
        i.putExtra("data",data);
        i.putExtra("hora",hora);
        startActivity(i);
    }

    //
    private void funcavanc() {
        cidadedest = edtcidadedest.getText().toString();
        endereçodest = edtendereçodest.getText().toString();

        if(cidadedest.equals("")||endereçodest.equals("")){
            Toast.makeText(this,"Preencha todos os campos!",Toast.LENGTH_LONG).show();
        }
        else {
            Intent i = new Intent(DestinoViagem.this, Viagemconfig.class);
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
    }
}
