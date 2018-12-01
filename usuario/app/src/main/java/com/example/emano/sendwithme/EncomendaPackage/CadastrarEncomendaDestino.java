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
    private String cidade1;
    private String endereco1;
    private String cidade;
    private String endereco;

    private Button avancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_encomenda_destino);

        setView();

        Bundle extras = getIntent().getExtras();

        cidade1 = extras.getString("cidadeOrigem");
        endereco1 = extras.getString("enderecoOrigem");

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cidade = cidadeDestinoEncomenda.getText().toString();
                endereco = enderecoDestinoencomenda.getText().toString();

                Intent intent = new Intent(CadastrarEncomendaDestino.this, CadastrarEncomendaDescricao.class);
                intent.putExtra("cidadeDestino", String.valueOf(cidade));
                intent.putExtra("enderecoDestino", String.valueOf(endereco));
                intent.putExtra("enderecoOrigem", String.valueOf(endereco1));
                intent.putExtra("cidadeOrigem",String.valueOf(cidade1));

                startActivity(intent);
            }
        });



    }

    public void setView(){

        cidadeDestinoEncomenda = (EditText) findViewById(R.id.editCidadeDestinoEncomenda);
        enderecoDestinoencomenda = (EditText) findViewById(R.id.editEnderecoDestinoEncomenda);
        avancar = (Button) findViewById(R.id.btnAvancarCadastro2);

    }

}

