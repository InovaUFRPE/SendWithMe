package com.example.emano.sendwithme.EncomendaPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.emano.sendwithme.R;

public class InfoEncomenda extends AppCompatActivity {

    TextView nome;
    TextView origem;
    TextView destino;
    TextView descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_encomenda);

        setView();

        Intent intent = getIntent();

        String nome1 = intent.getStringExtra("nomeObjeto");
        String origem1 = intent.getStringExtra("origem");
        String destino1 = intent.getStringExtra("destino");
        String descricao1 = intent.getStringExtra("descricao");

        nome.setText(nome1);
        origem.setText(origem1);
        destino.setText(destino1);
        descricao.setText(descricao1);

    }

    public void voltarListarEncomendas(View view){

        Intent intent  = new Intent(InfoEncomenda.this, ListarEncomendas.class);
        startActivity(intent);

    }

    public void setView(){

        nome = (TextView) findViewById(R.id.txtNomeObjetoInfoLista);
        origem = (TextView) findViewById(R.id.txtOrigemEncomendaInfoLista);
        destino = (TextView) findViewById(R.id.txtDestinoEncomendaInfoLista);
        descricao = (TextView) findViewById(R.id.txtDescricaoEncomendaLista);

    }

}
