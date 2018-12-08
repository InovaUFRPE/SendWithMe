package com.example.emano.sendwithme.EncomendaPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.emano.sendwithme.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_encomenda_selecionada);
        setView();
        

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
