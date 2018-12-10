package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import projetosendwithmemotorista.sendwithmemotorista.Activity.Chat.ListaChat;
import projetosendwithmemotorista.sendwithmemotorista.Activity.LoginMotorista.LoginActivity;
import projetosendwithmemotorista.sendwithmemotorista.Activity.TelaPerfil.TelaPerfil;
import projetosendwithmemotorista.sendwithmemotorista.R;

public class TelaInicial extends AppCompatActivity {

    private AlertDialog caixa;
    private Button btnnovacarona;
    private Button buttonperfil;
    private Button buttonlistadecarona;
    private Button btnlista;
    private Button btnsair;
    private Button btnchat;
    private String titulo;
    private String cont;
    private FirebaseAuth.AuthStateListener authStateListener;
    private Button historicoencomenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth authData) {
                if(authData == null){
                    Intent intent = new Intent(TelaInicial.this, LoginActivity.class);
                    startActivity(intent);

                }
            }
        };

        historicoencomenda = findViewById(R.id.btnhistencomendas);
        historicoencomenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(TelaInicial.this, HistoricoEncomendas.class);
                startActivity(n);
                finish();
            }
        });

        btnnovacarona = findViewById(R.id.btnnovacarona);
        buttonlistadecarona = findViewById(R.id.btnlista);
        buttonperfil = findViewById(R.id.buttonperfil);
        btnlista = findViewById(R.id.btnlista);
        btnsair = findViewById(R.id.btnsair);
        btnchat = findViewById(R.id.btnchat);


        btnnovacarona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titulo = "Deseja cadastrar uma nova carona?";
                cont = "teste";
                Intent i = new Intent(TelaInicial.this, InicioViagem.class);
                startActivity(i);
                finish();
            }
        });

        btnsair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(TelaInicial.this, LoginActivity.class));
                TelaInicial.this.finish();

            }
        });
        buttonperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TelaInicial.this, TelaPerfil.class));
                finish();
            }
        });

        buttonlistadecarona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TelaInicial.this, ListaViagens.class));
                finish();
            }
        });
        btnchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TelaInicial.this,ListaChat.class));
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
