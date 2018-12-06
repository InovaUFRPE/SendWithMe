package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import projetosendwithmemotorista.sendwithmemotorista.R;

public class StatusViagem extends AppCompatActivity {

    TextView textoStatus = findViewById(R.id.textoStatus);
    TextView statusDaViagem = findViewById(R.id.statusDaViagem);

    ListView listaPassageiros = findViewById(R.id.listaPassageiros);

    Button botaoVoltar = findViewById(R.id.botaoVoltar);
    Button botaoStatus = findViewById(R.id.botaoStatus);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_viagem);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StatusViagem.this, ListaViagens.class);
                startActivity(i);
                StatusViagem.this.finish();
            }
        });

        botaoStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status();

            }
        });

    }

    private void status(){
        final AlertDialog status = new AlertDialog.Builder(StatusViagem.this).create();
        status.setTitle("Mudar o status da viagem?");
        if (statusDaViagem.getText() == "Aguardado início da viagem"){
            status.setMessage("Deseja mudar o status da viagem de Em Aguardo para Iniciada?");
        }
        if (statusDaViagem.getText() == "Viagem em adamento"){
            status.setMessage("Deseja mudar o status da viagem de Iniciada para Finalizada?");
        }

        status.setButton(DialogInterface.BUTTON_POSITIVE, "Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (statusDaViagem.getText() == "Aguardado início da viagem"){
                    statusDaViagem.setText("Viagem em adamento");
                    botaoStatus.setText("Finalizar");
                }

                if (statusDaViagem.getText() == "Viagem em adamento"){
                    Toast finalizada = new Toast(StatusViagem.this);
                    Toast.makeText(StatusViagem.this, "Viagem finalizada", Toast.LENGTH_LONG).show();
                    finalizada.show();
                }
                status.dismiss();
            }
        });
        status.setButton(DialogInterface.BUTTON_NEGATIVE, "Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                status.dismiss();
            }
        });
    }
}
