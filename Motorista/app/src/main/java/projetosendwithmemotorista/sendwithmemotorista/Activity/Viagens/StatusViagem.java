package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;

import projetosendwithmemotorista.sendwithmemotorista.Entidades.Usuarios;
import projetosendwithmemotorista.sendwithmemotorista.R;

public class StatusViagem extends AppCompatActivity {

    private TextView textoStatus;
    private TextView statusDaViagem;

    private ListView listaPassageiros;

    private Button botaoVoltar;
    private Button botaoStatus;

    private String idviagemselect;

    private ArrayList<String> listapass;
    private ArrayAdapter<String> listaadapter;

    private DatabaseReference databaseReferenceviagemusuario = FirebaseDatabase.getInstance().getReference().child("ViagemUsuario");
    private DatabaseReference databaseReferenceviagem = FirebaseDatabase.getInstance().getReference().child("Viagens");
    private DatabaseReference viagematual;
    private DatabaseReference viagemusuarioatual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_viagem);

        //
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idviagemselect = extras.getString("idviagem");
            viagematual = databaseReferenceviagem.child(idviagemselect);
            viagemusuarioatual = databaseReferenceviagemusuario.child(idviagemselect);
            settarstatustela();
        }
        else{
            Toast.makeText(this,"Algo deu errado, tente novamente",Toast.LENGTH_SHORT).show();
        }

        //
        textoStatus = findViewById(R.id.textoStatus);
        statusDaViagem = findViewById(R.id.statusDaViagem);

        listaPassageiros = findViewById(R.id.listaPassageiros);

        botaoVoltar = findViewById(R.id.botaoVoltar);
        botaoStatus = findViewById(R.id.botaoStatus);

        //
        setalistapass();
        //

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

    //
    private void setalistapass() {


        listapass = new ArrayList<>();
        listaadapter = new ArrayAdapter<>(this,R.layout.activity_status_viagem,R.id.listaPassageiros, listapass);
        viagemusuarioatual.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Usuarios usuario = ds.getValue(Usuarios.class);
                    String nomeemail = usuario.getNome() + " " + usuario.getSobrenome() + "\n" + usuario.getEmail();
                    listapass.add(nomeemail);

                }
                listaPassageiros.setAdapter(listaadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StatusViagem.this,"Algo deu errado!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    //

    private void status(){
        final AlertDialog status = new AlertDialog.Builder(StatusViagem.this).create();
        status.setTitle("Mudar o status da viagem?");

        status.setButton(DialogInterface.BUTTON_POSITIVE, "Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

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

    //
    private void settarstatus(){
        viagematual.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ViagemFB vg = dataSnapshot.getValue(ViagemFB.class);
                String status = vg.getStatus();
                if(status.equals("Aguardando o início da viagem")){
                    String novostatus = "Viagem em andamento";
                    vg.setStatus(novostatus);
                    viagematual.setValue(vg);
                    statusDaViagem.setText(novostatus);
                    botaoStatus.setText("Finalizar");
                }
                if(status.equals("Viagem em andamento")){
                    deletarViagemFinalizada();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StatusViagem.this,"Algo deu errado",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //
    private void settarstatustela(){
        viagematual.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ViagemFB vg = dataSnapshot.getValue(ViagemFB.class);
                statusDaViagem.setText(vg.getStatus());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StatusViagem.this,"Algo deu errado",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deletarViagemFinalizada(){
       /* DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
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
    */}
}
