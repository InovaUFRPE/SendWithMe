package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

    private String vartroca;

    private Integer DIA;
    private Integer MES;
    private Integer ANO;
    private Integer HORARIO;
    private Integer MIN;

    private Integer DIAt;
    private Integer MESt;
    private Integer ANOt;
    private Integer HORARIOt;
    private Integer MINt;

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

        vartroca = "0";

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

            edtcidade.setText(cidade);
            edtendereço.setText(endereço);

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


        //filtro basic
        if (cidade.equals("")||endereço.equals("")||dia.equals("")||mes.equals("")||ano.equals("")||horario.equals("")||min.equals("")){
            ////
            Toast.makeText(this,"Preencha todos os campos!",Toast.LENGTH_SHORT).show();
            vartroca="1";
        }
        else{
            DIA = Integer.parseInt(dia);
            MES = Integer.parseInt(mes);
            ANO = Integer.parseInt(ano);
            HORARIO = Integer.parseInt(horario);
            MIN = Integer.parseInt(min);


            //filtro hora
            if(MIN > 59){
                vartroca = "1";
            }
            if(HORARIO==24 & MIN!= 0){
                vartroca = "1";
            }

            //filtro data
            if(ANO==0){
                vartroca="1";
            }
            if(DIA==0){
                vartroca = "1";
            }
            if(MES > 12 || MES==0){
                vartroca = "1";
            }
            if(MES==1 || MES==3 || MES==5 || MES==7 || MES==8 || MES==10 || MES==12 ){
                if(DIA > 31){
                    vartroca = "1";
                }
            }
            if(MES==4 || MES==6 || MES==9 || MES==11){
                if(DIA > 30){
                    vartroca = "1";
                }
            }
            if(MES==2){
                if(DIA>28){
                    vartroca = "1";
                }
            }

            //filtro horario e data atual
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy/HH/mm");
            String date = df.format(Calendar.getInstance().getTime());
            String[] datassplit = date.split("/");
            DIAt = Integer.parseInt(datassplit[0]);
            MESt = Integer.parseInt(datassplit[1]);
            ANOt = Integer.parseInt(datassplit[2]);
            HORARIOt = Integer.parseInt(datassplit[3]);
            MINt = Integer.parseInt(datassplit[4]);

            //filtro master

            if(ANO<ANOt){
                vartroca = "1";
            }
            if(ANOt.equals(ANO)){
                if(MES<MESt){
                    vartroca="1";
                }
                if(MES.equals(MESt)){
                    if(DIA<DIAt){
                        vartroca="1";
                    }
                    if(DIA.equals(DIAt)){
                        if(HORARIO<HORARIOt){
                            vartroca="1";
                        }
                        if(HORARIO.equals(HORARIOt)){
                            if(MIN<MINt){
                                vartroca="1";
                            }
                            if(MIN.equals(MINt)){
                                vartroca="0";
                            }
                        }
                    }
                }
            }


            //
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

            if(vartroca=="0"){
                trocatela();
                vartroca="0";
            }
            else {
                vartroca="0";
            }
        }
    }
    private void trocatela(){
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
