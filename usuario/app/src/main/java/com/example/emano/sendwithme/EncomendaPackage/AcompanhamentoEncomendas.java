package com.example.emano.sendwithme.EncomendaPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.emano.sendwithme.R;

public class AcompanhamentoEncomendas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acompanhamento_encomendas);
    }

    public void encomendasSemMotoristas(View view){

        Intent intent = new Intent(AcompanhamentoEncomendas.this, ListarEncomendas.class);
        startActivity(intent);

    }

    public void encomendasEmAndamento(View view){

        Intent intent1 = new Intent(AcompanhamentoEncomendas.this, ListarEncomendasSelecionadas.class);
        startActivity(intent1);

    }

    public void encomendasFinalizadas(View view){

        Intent intent2 = new Intent(AcompanhamentoEncomendas.this, ListarEncomendasFinalizadas.class);
        startActivity(intent2);

    }

}
