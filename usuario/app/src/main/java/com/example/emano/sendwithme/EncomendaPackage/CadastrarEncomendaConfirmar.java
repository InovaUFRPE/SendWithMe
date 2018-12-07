package com.example.emano.sendwithme.EncomendaPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emano.sendwithme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastrarEncomendaConfirmar extends AppCompatActivity {

    private TextView nomeObjeto;
    private TextView origemEncomenda;
    private TextView destinoEncomenda;
    private TextView descricaoEncomenda;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference database;
    private Button voltar;
    private Button cadastrar;
    private String cidadeOrigem1;
    private String cidadeDestino1;
    private String enderecoOrigem1;
    private String enderecoDestino1;
    private String nomeObjeto1;
    private String descricaoEncomenda1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_encomenda_confirmar);

        setView();

        Bundle extras = getIntent().getExtras();

        cidadeOrigem1  = extras.getString("cidadeOrigem");
        cidadeDestino1 = extras.getString("cidadeDestino");
        enderecoOrigem1 = extras.getString("enderecoOrigem");
        enderecoDestino1 = extras.getString("enderecoDestino");
        nomeObjeto1 = extras.getString("nomeObjeto");
        descricaoEncomenda1 = extras.getString("descricaoEncomenda");

        nomeObjeto.setText(nomeObjeto1);
        origemEncomenda.setText(enderecoOrigem1 + ", " + cidadeOrigem1);
        destinoEncomenda.setText(enderecoDestino1 + ", " + cidadeDestino1);
        descricaoEncomenda.setText(descricaoEncomenda1);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               database = FirebaseDatabase.getInstance().getReference("Encomendas").child(user.getUid());

                Encomenda encomenda = new Encomenda();
                encomenda.setIdUsuario(user.getUid());
                encomenda.setNomeObjeto(nomeObjeto1);
                encomenda.setDescricao(descricaoEncomenda1);
                encomenda.setCidadeOrigem(cidadeOrigem1);
                encomenda.setEnderecoOrigem(enderecoOrigem1);
                encomenda.setCidadeDestino(cidadeDestino1);
                encomenda.setEndrerecoDestino(enderecoDestino1);
                encomenda.setStatus("Aguardando entregador");

                database.push().setValue(encomenda);


                Toast.makeText(CadastrarEncomendaConfirmar.this,"Encomenda cadastrada com sucesso!",Toast.LENGTH_SHORT).show();

            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(CadastrarEncomendaConfirmar.this, CadastrarEncomendaDescricao.class);
                startActivity(intent1);

            }
        });

    }

    public void setView(){

        nomeObjeto = (TextView) findViewById(R.id.txtNomeObjetoInfoLista);
        origemEncomenda = (TextView) findViewById(R.id.txtOrigemEncomendaInfoLista);
        destinoEncomenda = (TextView) findViewById(R.id.txtDestinoEncomendaInfo);
        descricaoEncomenda = (TextView) findViewById(R.id.txtDescricaoEncomenda);
        voltar = (Button) findViewById(R.id.btnVoltarCadastroEncomendaDescricao);
        cadastrar = (Button) findViewById(R.id.btnCadastrarEncomenda);

    }


}
