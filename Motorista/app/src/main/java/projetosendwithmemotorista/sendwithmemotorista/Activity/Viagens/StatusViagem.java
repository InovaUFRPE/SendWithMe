package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import projetosendwithmemotorista.sendwithmemotorista.R;

public class StatusViagem extends AppCompatActivity {

    private TextView textoStatus;
    private TextView statusDaViagem;

    private ListView listaPassageiros;

    private Button botaoVoltar;
    private Button botaoStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_viagem);

        textoStatus = findViewById(R.id.textoStatus);
        statusDaViagem = findViewById(R.id.statusDaViagem);

        listaPassageiros = findViewById(R.id.listaPassageiros);

        botaoVoltar = findViewById(R.id.botaoVoltar);
        botaoStatus = findViewById(R.id.botaoStatus);

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

        status.setButton(DialogInterface.BUTTON_POSITIVE, "Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(statusDaViagem.getText().toString().equals("Aguardado início da viagem")){
                    statusDaViagem.setText("Viagem em adamento");
                    botaoStatus.setText("Finalizar");
                } else {
                    statusDaViagem.setText("Viagem finalizada");
                    deletarViagemFinalizada();
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

        status.show();
    }

    private void deletarViagemFinalizada(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("Viagens").orderByChild("viagemUID").equalTo("Olinda");

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
