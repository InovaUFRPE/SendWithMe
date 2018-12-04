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
        email = (AutoCompleteTextView) findViewById(R.id.email);

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
                String emailString = email.getText().toString().trim();
                if (emailString.isEmpty()) {
                    email.setError("Campo vazio!");
                    Toast.makeText(ResetActivity.this, "Campo Email vazio! Preencha corretamente!",Toast.LENGTH_SHORT ).show();
                }else {
                    reset();
                }
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();

    }


    public void reset(){

        firebaseAuth
                .sendPasswordResetEmail(email.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        if(task.isSuccessful() ){
                            Intent i = new Intent(ResetActivity.this, LoginActivity.class);
                            startActivity(i);
                            finish();
                            email.setText("");
                            Toast.makeText(
                                    ResetActivity.this,
                                    "Recuperação de senha iniciado. Verifique no seu Email na Caixa de Entrada ou no Spam.",
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
    public boolean validarcampos(){
        boolean erro = true;
        if(validaremail()){
            erro = false;
        }
        return erro;
    }
    private boolean validaremail() {
        boolean erro = false;
        String emailString = email.getText().toString().trim();
        if (emailString.isEmpty()) {
            erro = true;
            email.setError("Campo vazio!");

        }
        return erro;
    }
}
