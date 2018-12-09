package com.example.emano.sendwithme.EncomendaPackage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.motoristaPackage.InfoMotorista;
import com.example.emano.sendwithme.MotoristaPackage.Motorista;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoEncomendaSelecionada extends AppCompatActivity {

    private TextView nomeMotorista;
    private TextView nomeObjeto;
    private TextView data;
    private TextView descricao;
    private TextView origem;
    private TextView destino;
    private Button infoMotorista;
    private Button finalizaEncomenda;
    private Button chatMotorista;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference2;
    Motorista motorista = new Motorista();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_encomenda_selecionada);
        setView();

        final Intent intent = getIntent();

        final String idMotorista = intent.getStringExtra("idMotorista");
        final String idUsuario = intent.getStringExtra("idUsuario");
        final String idEncomenda = intent.getStringExtra("idEncomenda");
        final String nomeObjeto1 = intent.getStringExtra("nomeObjeto");
        final String origem1 = intent.getStringExtra("origem");
        final String destino1 = intent.getStringExtra("destino");
        final String hora1 = intent.getStringExtra("hora");
        final String data1 = intent.getStringExtra("data");
        final String descricao1 = intent.getStringExtra("descricao");
        final String idEncomendaSelecionada = intent.getStringExtra("idEncomendaSelecionada");

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

                Intent intent1 = new Intent(InfoEncomendaSelecionada.this, InfoMotorista.class);
                intent1.putExtra("nome", String.valueOf(motorista.getNome()));
                intent1.putExtra("sobrenome", String.valueOf(motorista.getSobrenome()));
                intent1.putExtra("sexo", String.valueOf(motorista.getSexo()));
                intent1.putExtra("email", String.valueOf(motorista.getEmail()));
                startActivity(intent1);

            }
        });

        finalizaEncomenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference2 = FirebaseDatabase.getInstance().getReference().child("EncomendasSelecionadas").child(idEncomendaSelecionada).child("status");
                databaseReference2.setValue("Finalizada");
                Intent intent1 = new Intent(InfoEncomendaSelecionada.this, ListarEncomendasSelecionadas.class);
                startActivity(intent1);
                Toast.makeText(InfoEncomendaSelecionada.this,"Encomenda finalizada com sucesso!",Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void setView(){

        nomeMotorista = (TextView) findViewById(R.id.txtnomeMotoristaEncomenda);
        nomeObjeto = (TextView) findViewById(R.id.txtNomeObjetoEncomendaInfo);
        data = (TextView) findViewById(R.id.txtDataEncomendaInfo);
        descricao = (TextView) findViewById(R.id.txtDescricaoEncomendaInfo);
        origem = (TextView) findViewById(R.id.txtOrigemEncomendaInfo);
        destino = (TextView) findViewById(R.id.txtDestinoEncomendaInfo);
        infoMotorista = (Button) findViewById(R.id.btnInfoMotoristaEncomenda);
        finalizaEncomenda = (Button) findViewById(R.id.btnFinalizarEncomenda);
        chatMotorista = (Button) findViewById(R.id.btnChatMotoristaEncomenda);

    }

}
