package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import projetosendwithmemotorista.sendwithmemotorista.R;

public class TelaInicial extends AppCompatActivity {

    private AlertDialog caixa;
    private Button btnnovacarona;
    private Button btnperfil;
    private Button btnlista;
    private Button btnsair;
    private String titulo;
    private String cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        btnnovacarona = findViewById(R.id.btnnovacarona);
        btnperfil = findViewById(R.id.btnperfil);
        btnlista = findViewById(R.id.btnlista);
        btnsair = findViewById(R.id.btnsair);

        btnnovacarona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titulo = "Deseja cadastrar uma nova carona?";
                cont = "teste";
                Intent i = new Intent(TelaInicial.this, InicioViagem.class);
                startActivity(i);
            }
        });

        btnsair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titulo = "Deseja mesmo sair?";
                cont = "teste";

            }
        });

    }


    private void abre_caixa1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo);
        builder.setMessage(cont);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //
            }
        });
        caixa = builder.create();
        caixa.show();
    }
}
