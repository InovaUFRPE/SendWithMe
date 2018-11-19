package com.example.emano.sendwithme.MotoristaPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.emano.sendwithme.MotoristaPackage.Motorista;
import com.example.emano.sendwithme.R;

import java.util.ArrayList;

public class ListarMotoristas extends AppCompatActivity {

    ArrayList<Motorista> motoristas = new ArrayList<>();
    ArrayAdapter adapter;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_motoristas);

        lista = (ListView) findViewById(R.id.motoristas_view);

    }
}
