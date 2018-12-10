package com.example.emano.sendwithme.EncomendaPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emano.sendwithme.R;

public class CadastrarEncomendaDescricao extends AppCompatActivity {

    private EditText nomeObjeto;
    private EditText descricaoEncomenda;
    private Button avancar;
    private Button voltar;
    private String cidadeOrigem;
    private String enderecoOrigem;
    private String cidadeDestino;
    private String enderecoDestino;
    private String nomeObjeto1;
    private String descricaoEncomenda1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_encomenda_descricao);

        setView();

        Bundle extras = getIntent().getExtras();

        cidadeOrigem = extras.getString("cidadeOrigem");
        enderecoOrigem = extras.getString("enderecoOrigem");
        cidadeDestino = extras.getString("cidadeDestino");
        enderecoDestino = extras.getString("enderecoDestino");

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nomeObjeto1 = nomeObjeto.getText().toString();
                descricaoEncomenda1 = descricaoEncomenda.getText().toString();

                if(nomeObjeto1.isEmpty() || descricaoEncomenda1.isEmpty()){

                    Toast.makeText(CadastrarEncomendaDescricao.this,"Preencha todos os campos!",Toast.LENGTH_SHORT).show();

                }else {

                    Intent intent1 = new Intent(CadastrarEncomendaDescricao.this, CadastrarEncomendaConfirmar.class);

                    intent1.putExtra("cidadeOrigem", String.valueOf(cidadeOrigem));
                    intent1.putExtra("cidadeDestino", String.valueOf(cidadeDestino));
                    intent1.putExtra("enderecoOrigem", String.valueOf(enderecoOrigem));
                    intent1.putExtra("enderecoDestino", String.valueOf(enderecoDestino));
                    intent1.putExtra("nomeObjeto", String.valueOf(nomeObjeto1));
                    intent1.putExtra("descricaoEncomenda", String.valueOf(descricaoEncomenda1));

                    startActivity(intent1);
                }
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(CadastrarEncomendaDescricao.this, CadastrarEncomendaDestino.class);
                intent2.putExtra("cidadeOrigem", cidadeOrigem);
                intent2.putExtra("enderecoOrigem", enderecoOrigem);
                intent2.putExtra("cidadeDestino", cidadeDestino);
                intent2.putExtra("enderecoDestino", enderecoDestino);
                startActivity(intent2);

            }
        });

    }

    public void setView(){

        nomeObjeto = (EditText) findViewById(R.id.editNomeObjeto);
        descricaoEncomenda = (EditText) findViewById(R.id.editDescricaoEncomenda);
        avancar = (Button) findViewById(R.id.btnAvancarConfirmaEncomenda);
        voltar = (Button) findViewById(R.id.btnVoltarCadastroDestinoEncomenda);

    }

}
