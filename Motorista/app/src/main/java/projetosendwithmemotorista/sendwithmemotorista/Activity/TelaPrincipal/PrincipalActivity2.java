package projetosendwithmemotorista.sendwithmemotorista.Activity.TelaPrincipal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import projetosendwithmemotorista.sendwithmemotorista.Activity.LoginMotorista.LoginActivity;
import projetosendwithmemotorista.sendwithmemotorista.Activity.TelaPerfil.TelaPerfil;
import projetosendwithmemotorista.sendwithmemotorista.Activity.TelaPrincipalMapa.PrincipalActivity;
import projetosendwithmemotorista.sendwithmemotorista.R;

public class PrincipalActivity2 extends AppCompatActivity {

    private Button buttonsair;
    private Button buttonperfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal2);
        buttonsair = (Button) findViewById(R.id.button2);
        buttonsair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sair();
            }
        });
        buttonperfil = (Button) findViewById(R.id.button);
        buttonperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Perfil();
            }
        });
    }
    public void Sair(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(PrincipalActivity2.this, LoginActivity.class));
        PrincipalActivity2.this.finish();

    }
    public void Perfil(){
        startActivity(new Intent(PrincipalActivity2.this, TelaPerfil.class));
    }
}
