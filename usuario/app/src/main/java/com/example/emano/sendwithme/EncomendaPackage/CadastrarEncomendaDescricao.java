package com.example.emano.sendwithme.EncomendaPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.emano.sendwithme.R;

public class CadastrarEncomendaDescricao extends AppCompatActivity {

    private EditText nomeObjeto;
    private EditText descricaoEncomenda;
    private Button avancar;
    private Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_encomenda_descricao);

        setView();

        Intent intent = getIntent();

        final String cidadeOrigem = intent.getStringExtra("cidadeOrigem");
        final String enderecoOrigem = intent.getStringExtra("enderecoOrigem");
        final String cidadeDestino = intent.getStringExtra("cidadeDestino");
        final String enderecoDestino = intent.getStringExtra("enderecoDestino");
        final String nomeObjeto1 = nomeObjeto.getText().toString();
        final String descricaoEncomenda1 = descricaoEncomenda.getText().toString();

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(CadastrarEncomendaDescricao.this, CadastrarEncomendaConfirmar.class);
                intent1.putExtra("cidadeOrigem",cidadeOrigem);
                intent1.putExtra("cidadeDestino", cidadeDestino);
                intent1.putExtra("enderecoOrigem", enderecoOrigem);
                intent1.putExtra("enderecoDestino", enderecoDestino);
                intent1.putExtra("nomeObjeto", nomeObjeto1);
                intent1.putExtra("descricaoEncomenda", descricaoEncomenda1);
                startActivity(intent1);

            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(CadastrarEncomendaDescricao.this, CadastrarEncomendaDestino.class);
                startActivity(intent2);

            }
        });

    }

    public void setView(){

        nomeObjeto = (EditText) findViewById(R.id.editNomeObjeto);
        descricaoEncomenda = (EditText) findViewById(R.id.editDescricaoEncomenda);
        avancar = (Button) findViewById(R.id.btnAvancarCadastro2);
        voltar = (Button) findViewById(R.id.btnVoltarCadastroDestinoEncomenda);

    }

}
