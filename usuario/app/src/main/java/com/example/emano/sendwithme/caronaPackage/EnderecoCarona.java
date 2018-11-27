package com.example.emano.sendwithme.CaronaPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.MotoristaPackage.ListarMotoristas;

public class EnderecoCarona extends AppCompatActivity {

    TextView origem;
    TextView destino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco_carona);
        setView();
    }

    public void avancarCarona(View view){

        String origem1 = origem.getText().toString();
        String destino1 = destino.getText().toString();

        Intent intent = new Intent(EnderecoCarona.this, ListarMotoristas.class);
        intent.putExtra("origem", origem1);
        intent.putExtra("destino", destino1);
        startActivity(intent);

    }

    public void setView(){

        origem = (TextView) findViewById(R.id.editOrigemCarona);
        destino = (TextView) findViewById(R.id.editDestinoCarona);

    }

}
