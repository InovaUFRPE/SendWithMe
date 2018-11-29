package com.example.emano.sendwithme.EncomendaPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.emano.sendwithme.R;

public class CadastrarEncomendaDestino extends AppCompatActivity {

    private EditText cidadeDestinoEncomenda;
    private EditText enderecoDestinoencomenda;

    private Button avancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_encomenda_destino);

        setView();

        Intent intent = getIntent();

        final String cidade1 = intent.getStringExtra("cidadeOrigem");
        final String endereco1 = intent.getStringExtra("enderecoOrigem");
        final String cidade = cidadeDestinoEncomenda.getText().toString();
        final String endereco = enderecoDestinoencomenda.getText().toString();

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CadastrarEncomendaDestino.this, CadastrarEncomendaDestino.class);
                intent.putExtra("cidadeDestino", cidade);
                intent.putExtra("enderecoDestino", endereco);
                intent.putExtra("enderecoOrigem", endereco1);
                intent.putExtra("cidadeOrigem",cidade1);

                startActivity(intent);
            }
        });



    }

    public void setView(){

        cidadeDestinoEncomenda = (EditText) findViewById(R.id.editCidadeDestinoEncomenda);
        enderecoDestinoencomenda = (EditText) findViewById(R.id.editEnderecoDestinoEncomenda);
        avancar = (Button) findViewById(R.id.btnAvancarCadastro1);

    }

}

