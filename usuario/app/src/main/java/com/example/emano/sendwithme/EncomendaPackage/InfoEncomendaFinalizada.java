package com.example.emano.sendwithme.EncomendaPackage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.emano.sendwithme.MotoristaPackage.Motorista;
import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.MotoristaPackage.InfoMotorista;
import com.example.emano.sendwithme.MotoristaPackage.Motorista;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoEncomendaFinalizada extends AppCompatActivity {

    private TextView nomeMotorista;
    private TextView nomeObjeto;
    private TextView data;
    private TextView descricao;
    private TextView origem;
    private TextView destino;
    private Button infoMotorista;
    private DatabaseReference databaseReference;
    Motorista motorista = new Motorista();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_encomenda_finalizada);

        setView();

        final Intent intent = getIntent();

        final String idMotorista = intent.getStringExtra("idMotorista");
        final String idUsuario = intent.getStringExtra("idUsuario");
        final String nomeObjeto1 = intent.getStringExtra("nomeObjeto");
        final String origem1 = intent.getStringExtra("origem");
        final String destino1 = intent.getStringExtra("destino");
        final String hora1 = intent.getStringExtra("hora");
        final String data1 = intent.getStringExtra("data");
        final String descricao1 = intent.getStringExtra("descricao");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("usuario").child(idMotorista);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                motorista = dataSnapshot.getValue(Motorista.class);
                nomeMotorista.setText(motorista.getNome());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        nomeObjeto.setText(nomeObjeto1);
        origem.setText(origem1);
        destino.setText(destino1);
        descricao.setText(descricao1);
        data.setText(data1 + " às " + hora1);

        infoMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(InfoEncomendaFinalizada.this, InfoMotorista.class);
                intent1.putExtra("nome", String.valueOf(motorista.getNome()));
                intent1.putExtra("sobrenome", String.valueOf(motorista.getSobrenome()));
                intent1.putExtra("sexo", String.valueOf(motorista.getSexo()));
                intent1.putExtra("email", String.valueOf(motorista.getEmail()));
                startActivity(intent1);

            }
        });




    }

    private void setView(){

        nomeMotorista = (TextView) findViewById(R.id.txtnomeMotoristaEncomendaInfoFinalizada);
        nomeObjeto = (TextView) findViewById(R.id.txtNomeObjetoEncomendaInfoFinalizada);
        data = (TextView) findViewById(R.id.txtDataEncomendaInfoFinalizada);
        descricao = (TextView) findViewById(R.id.txtDescricaoEncomendaInfoFinalizada);
        origem = (TextView) findViewById(R.id.txtOrigemEncomendaInfoFinalizada);
        destino = (TextView) findViewById(R.id.txtDestinoEncomendaInfoFinalizada);
        infoMotorista = (Button) findViewById(R.id.btnInfoMotoristaEncomendaFinalizada);

    }

}
