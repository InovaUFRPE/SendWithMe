package com.example.emano.sendwithme.homePackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;

import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.caronaPackage.EnderecoCarona;

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

}
