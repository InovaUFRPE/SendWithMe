package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import projetosendwithmemotorista.sendwithmemotorista.R;

public class InicioViagem extends AppCompatActivity {

    private String cidadedest;
    private String endereçodest;
    private String cidade;
    private String endereço;
    private String data;
    private String hora;
    private String encomendas;
    private Integer assentos;

    private EditText edtcidade;
    private EditText edtendereço;
    private EditText edtdata;
    private EditText edthora;

    private Button btnavançar;
    private Button btnvoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_viagem);

        //
        edtcidade = findViewById(R.id.Edtcity);
        edtendereço = findViewById(R.id.Edtend);
        edtdata = findViewById(R.id.Edtdata);
        edthora = findViewById(R.id.Edthora);

        //
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cidade = extras.getString("cidade");
            endereço = extras.getString("endereço");
            data = extras.getString("data");
            hora = extras.getString("hora");

            //
            edtcidade.setText(cidade);
            edtendereço.setText(endereço);
            edtdata.setText(data);
            edthora.setText(hora);
        }

        btnavançar = findViewById(R.id.Btnavanc);
        btnvoltar = findViewById(R.id.Btnvolt);

        //
        btnavançar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funcavanc();
            }
        });

        //
        btnvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funcvolt();
            }
        });

    }

    //
    private void funcvolt() {
        Intent y = new Intent(InicioViagem.this,TelaInicial.class);
        startActivity(y);
        finish();
    }

    //
    private void funcavanc() {
        cidade = edtcidade.getText().toString();
        endereço = edtendereço.getText().toString();
        data = edtdata.getText().toString();
        hora = edthora.getText().toString();

        if (cidade.equals("")||endereço.equals("")||data.equals("")||hora.equals("")){
            Toast.makeText(this,"Preencha todos os campos!",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(InicioViagem.this, DestinoViagem.class);
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

    public String getcidade() {return cidade; }

    public void setcidade(String cidade){this.cidade = cidade;}

    public String getendereco() {return endereço;}

    public void setendereco(String endereço) {this.endereço = endereço;}

    public String getdata() {return data;}

    public void setdata(String data) {this.data = data;}

    public String gethora() {return hora;}

    public void sethora(String hora) {this.hora = hora;}


}
