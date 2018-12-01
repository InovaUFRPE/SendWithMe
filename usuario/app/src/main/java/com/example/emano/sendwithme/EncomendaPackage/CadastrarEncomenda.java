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

                Toast.makeText(CadastrarEncomenda.this,"Funcionando",Toast.LENGTH_SHORT).show();

                cidade = cidadeOrigemEncomenda.getText().toString();
                endereco = enderecoOrigemencomenda.getText().toString();

                Intent intent = new Intent(CadastrarEncomenda.this, CadastrarEncomendaDestino.class);
                intent.putExtra("cidadeOrigem", String.valueOf(cidade));
                intent.putExtra("enderecoOrigem", String.valueOf(endereco));
                startActivity(intent);
            }
        });



    }


    public void setView(){

    cidadeOrigemEncomenda = (EditText) findViewById(R.id.editCidadeDestinoEncomenda);
    enderecoOrigemencomenda = (EditText) findViewById(R.id.editEnderecoDestinoEncomenda);
    avancar = (Button) findViewById(R.id.btnAvancarCadastro1);

    }

    /*public void fazerPedido(View view){

        EditText origem = (EditText) findViewById(R.id.editOrigemPedido);
        EditText destino = (EditText) findViewById(R.id.edtDestinoPedido);
        String origem1 = origem.getText().toString();
        String destino1 = destino.getText().toString();

        EditText titulo = (EditText) findViewById(R.id.editTítuloPedido);
        EditText nome = (EditText) findViewById(R.id.editNomeItem);
        String titulo1 = titulo.getText().toString();
        String nome1 = nome.getText().toString();

        if(origem1.isEmpty()){
            origem.setError("Campo em branco");
        }else if(destino1.isEmpty()){
            destino.setError("Campo em branco");
        }else if(titulo1.isEmpty()){
            titulo.setError("Campo em branco");
        }else if(nome1.isEmpty()){
            nome.setError("Campo em branco");
        }else {



            databaseReference = FirebaseDatabase.getInstance().getReference(user.getUid()).child("Encomendas");

            databaseReference.push().setValue(pedido);

            Intent intent = new Intent(getApplicationContext(), com.example.emano.sendwithme.homePackage.HomeDrawer.class);
            startActivity(intent);

            Toast.makeText(getApplicationContext(), "Pedido Salvo", Toast.LENGTH_LONG).show();*/

        }
