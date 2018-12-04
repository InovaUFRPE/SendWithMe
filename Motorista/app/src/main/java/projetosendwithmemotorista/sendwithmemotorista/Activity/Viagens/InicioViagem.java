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

    private String dia;
    private String mes;
    private String ano;
    private String horario;
    private String min;

    private EditText edtcidade;
    private EditText edtendereço;
    private EditText edtdia;
    private EditText edtmes;
    private EditText edtano;
    private EditText edthora;
    private EditText edtmin;

    private Button btnavançar;
    private Button btnvoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_viagem);

        //
        edtcidade = findViewById(R.id.Edtcity);
        edtendereço = findViewById(R.id.Edtend);

        //
        edtdia = findViewById(R.id.datadia);
        edtmes = findViewById(R.id.datames);
        edtano = findViewById(R.id.dataano);

        edthora = findViewById(R.id.datahora);
        edtmin = findViewById(R.id.datamin);

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

            if(!data.equals("")||!hora.equals("")){

                String[] partsd = data.split("/");
                dia = partsd[0];
                mes = partsd[1];
                ano = partsd[2];

                String[] partsh = hora.split(":");
                horario = partsh[0];
                min = partsh[1];

                edtdia.setText(dia);
                edtmes.setText(mes);
                edtano.setText(ano);
                edthora.setText(horario);
                edtmin.setText(min);
            }

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
        dia = edtdia.getText().toString();
        mes = edtmes.getText().toString();
        ano = edtano.getText().toString();
        horario = edthora.getText().toString();
        min = edtmin.getText().toString();

        if (dia.equals("00")||mes.equals("00")||horario.equals("00")||min.equals("00")||ano.equals("0000")){
            Toast.makeText(this,"0000 é invalido!",Toast.LENGTH_SHORT).show();
        }

        if (cidade.equals("")||endereço.equals("")||dia.equals("")||mes.equals("")||ano.equals("")||horario.equals("")||min.equals("")){
            Toast.makeText(this,"Preencha todos os campos!",Toast.LENGTH_SHORT).show();
        }
        else {
            if(dia.length()<2){
                dia = "0" + dia;
            }
            if(mes.length()<2){
                mes = "0" + mes;
            }
            if(horario.length()<2){
                horario = "0" + horario;
            }
            if(min.length()<2){
                min = "0" + min;
            }

            data = dia + "/" + mes + "/" + ano;
            hora = horario + ":" + min;
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
}
