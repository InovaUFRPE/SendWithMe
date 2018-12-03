package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import projetosendwithmemotorista.sendwithmemotorista.R;

public class ListaEncomendas extends AppCompatActivity {

    private Button btnavancar;
    private String cidadedest;
    private String endereçodest;
    private String cidade;
    private String endereço;
    private String data;
    private String hora;
    private String encomendas;

    private Integer assentos;

    private TextView encomendainfo;

    private EditText edtassdisp;

    private CheckBox encomendabox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_encomendas);
        btnavancar = findViewById(R.id.btnavancar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cidade = extras.getString("cidade");
            endereço = extras.getString("endereço");
            data = extras.getString("data");
            hora = extras.getString("hora");
            cidadedest = extras.getString("cidadedest");
            endereçodest = extras.getString("endereçodest");
            assentos = extras.getInt("assentos");
            encomendas = extras.getString("encomendas");
        }

        btnavancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funcacanvar();
            }

        });

    }

    private void funcacanvar() {


                Intent i = new Intent(ListaEncomendas.this, Viagemrevisao.class);
                i.putExtra("cidade", cidade);
                i.putExtra("endereço", endereço);
                i.putExtra("data", data);
                i.putExtra("hora", hora);
                i.putExtra("cidadedest", cidadedest);
                i.putExtra("endereçodest", endereçodest);
                i.putExtra("assentos", assentos);
                i.putExtra("encomendas", encomendas);
                startActivity(i);
            }



}
