package projetosendwithmemotorista.sendwithmemotorista.Activity.LoginMotorista;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens.InicioViagem;
import projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens.TelaInicial;
import projetosendwithmemotorista.sendwithmemotorista.R;

public class ResetActivity extends AppCompatActivity {

    private Button toolbar;
    private AutoCompleteTextView email;
    private FirebaseAuth firebaseAuth;
    private Button volta;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        volta = (Button) findViewById(R.id.volta);
        volta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ResetActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        toolbar = (Button) findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        email = (AutoCompleteTextView) findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();

    }




    private void init(){
        email = (AutoCompleteTextView) findViewById(R.id.email);
    }

    public void reset(){
        Intent i = new Intent(ResetActivity.this, TelaInicial.class);
        startActivity(i);
        finish();
        firebaseAuth
                .sendPasswordResetEmail(email.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful() ){
                            email.setText("");
                            Toast.makeText(
                                    ResetActivity.this,
                                    "Recuperação de acesso iniciado. Email enviado.",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                        else{
                            Toast.makeText(
                                    ResetActivity.this,
                                    "Falhou! Tente novamente." + "Verifique se o email informado está correto!",
                                    Toast.LENGTH_SHORT
                            ).show();

                        }
                    }
                });
    }
}
