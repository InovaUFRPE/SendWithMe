package com.example.emano.sendwithme.EncomendaPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.emano.sendwithme.R;

import org.w3c.dom.Text;

public class CadastrarEncomendaConfirmar extends AppCompatActivity {

    private TextView nomeObjeto;
    private TextView origemEncomenda;
    private TextView destinoEncomenda;
    private TextView descricaoEncomenda;

    private Button voltar;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_encomenda_confirmar);
    }

    public void setView(){

        nomeObjeto = (TextView) findViewById(R.id.txtNomeObjetoInfo);
        origemEncomenda = (TextView) findViewById(R.id.txtOrigemEncomendaInfo);
        destinoEncomenda = (TextView) findViewById(R.id.txtDestinoEncomendaInfo);
        descricaoEncomenda = (TextView) findViewById(R.id.txtDescricaoEncomenda);
        voltar = (Button) findViewById(R.id.btnVoltarCadastroEncomendaDescricao);
        cadastrar = (Button) findViewById(R.id.btnCadastrarEncomenda);

    }

}
