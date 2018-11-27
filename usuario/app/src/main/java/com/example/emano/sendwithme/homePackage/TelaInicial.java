package com.example.emano.sendwithme.HomePackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.CaronaPackage.EnderecoCarona;
import com.google.firebase.auth.FirebaseAuth;

public class TelaInicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
    }

    public void novaCarona(View view){

        Intent intent = new Intent(TelaInicial.this, EnderecoCarona.class);
        startActivity(intent);

    }

    public void sair(View view){

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(TelaInicial.this, Login.class));
        TelaInicial.this.finish();

    }

}
