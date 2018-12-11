package com.example.emano.sendwithme.MotoristaPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.emano.sendwithme.R;

import org.w3c.dom.Text;

public class InfoMotorista extends AppCompatActivity {

    TextView nome;
    TextView sexo;
    TextView email;
    TextView dataNascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_motorista);

        setView();

        Intent intent = getIntent();

        String nome1 = intent.getStringExtra("nome");
        String sobrenome1 = intent.getStringExtra("sobrenome");
        String sexo1 = intent.getStringExtra("sexo");
        String email1 = intent.getStringExtra("email");


        nome.setText(nome1 + " " + sobrenome1);
        sexo.setText(sexo1);
        email.setText(email1);

    }

    private void setView(){

        nome = (TextView) findViewById(R.id.txtNomeMotoristaInfoMotoristaInfo);
        sexo = (TextView) findViewById(R.id.txtSexoMotoristaInfoMotoristaInfo);
        email = (TextView) findViewById(R.id.txtEmailMotoristaInfoMotoristaInfo);

    }

}
