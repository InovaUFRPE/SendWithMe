package com.example.emano.sendwithme.PedidoPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emano.sendwithme.HomePackage.HomeDrawerActivity;
import com.example.emano.sendwithme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfirmarPedido extends AppCompatActivity {

    Button confirma;
    EditText titulo;
    EditText nome;
    String titulo1;
    String nome1;
    private DatabaseReference databaseReference;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    Pedido pedido = new Pedido();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);

    }

    public void ConfirmarPedido(View view) {

        setView();

        //Intent novaIntent = getIntent();
        Bundle novaIntent = getIntent().getExtras();

        String origem = novaIntent.getString("origem");
        String destino = novaIntent.getString("destino");

        pedido.setOrigem(origem);
        pedido.setDestino(destino);

        if (titulo1.isEmpty()) {
            titulo.setError("Campo em branco");
        } else if (nome1.isEmpty()) {
            nome.setError("Campo em branco");
        } else {

            pedido.setTitulo(titulo.getText().toString());
            pedido.setObjeto(nome.getText().toString());
            pedido.setIdUsuario(user.getUid());

            databaseReference = FirebaseDatabase.getInstance().getReference("Pedidos").child(user.getUid());

            databaseReference.push().setValue(pedido);

            Intent intent1 = new Intent(getApplicationContext(), HomeDrawerActivity.class);
            startActivity(intent1);

            Toast.makeText(getApplicationContext(), "Pedido Salvo", Toast.LENGTH_LONG).show();


        }


    }

    private void setView() {

       confirma = (Button) findViewById(R.id.btnConfirmarPedido);
       titulo = (EditText) findViewById(R.id.editTituloPedido);
       nome = (EditText) findViewById(R.id.editNomeObjeto);
       titulo1 = titulo.getText().toString();
       nome1 = nome.getText().toString();
    }

}
