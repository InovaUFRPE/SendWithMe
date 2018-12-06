package com.example.emano.sendwithme.homePackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.emano.sendwithme.ChatPackage.ListaChatActivity;
import com.example.emano.sendwithme.EncomendaPackage.CadastrarEncomenda;
import com.example.emano.sendwithme.EncomendaPackage.ListarEncomendas;
import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.caronaPackage.EnderecoCarona;
import com.google.firebase.auth.FirebaseAuth;

public class TelaInicial extends AppCompatActivity {

    private Button botaoListaChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        botaoListaChat = findViewById(R.id.btnListaChat);

        botaoListaChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaInicial.this, ListaChatActivity.class);
                startActivity(intent);


            }
        });
    }

    public void novaCarona(View view){

        Intent intent = new Intent(TelaInicial.this, EnderecoCarona.class);
        startActivity(intent);

    }

    public void novaEncomenda(View view){
        ///

        Intent intent1 = new Intent(TelaInicial.this, ListarEncomendas.class);
        startActivity(intent1);

    }

    public void sair(View view){

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(TelaInicial.this, Login.class));
        TelaInicial.this.finish();

    }

}
