package com.example.emano.sendwithme.EncomendaPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.homePackage.TelaInicial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastrarEncomenda extends AppCompatActivity {

    private EditText cidadeOrigemEncomenda;
    private EditText enderecoOrigemencomenda;
    private String cidade;
    private String endereco;

    private Button avancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_encomenda);

        setView();

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cidade = cidadeOrigemEncomenda.getText().toString();
                endereco = enderecoOrigemencomenda.getText().toString();

                if(cidade.isEmpty() || endereco.isEmpty()){

                    Toast.makeText(CadastrarEncomenda.this,"Preencha todos os campos!", Toast.LENGTH_SHORT).show();

                }else {

                    Intent intent = new Intent(CadastrarEncomenda.this, CadastrarEncomendaDestino.class);
                    intent.putExtra("cidadeOrigem", String.valueOf(cidade));
                    intent.putExtra("enderecoOrigem", String.valueOf(endereco));
                    startActivity(intent);
                }
            }
        });



    }


    public void setView(){

    cidadeOrigemEncomenda = (EditText) findViewById(R.id.editCidadeDestinoEncomenda);
    enderecoOrigemencomenda = (EditText) findViewById(R.id.editEnderecoDestinoEncomenda);
    avancar = (Button) findViewById(R.id.btnAvancarCadastro1);

    }

}
