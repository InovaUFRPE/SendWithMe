package projetosendwithmemotorista.sendwithmemotorista.Activity.TelaPerfil;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import projetosendwithmemotorista.sendwithmemotorista.Activity.LoginMotorista.LoginActivity;
import projetosendwithmemotorista.sendwithmemotorista.Entidades.Usuarios;
import projetosendwithmemotorista.sendwithmemotorista.Helper.PreferenciasAndroid;
import projetosendwithmemotorista.sendwithmemotorista.R;

public class EditarPerfil extends AppCompatActivity {

    private Button btnExcluirConta;
    private Context cont;
    private Usuarios usuarios;
    private TextView nome;
    private DatabaseReference databaseReference;
    private Button voltaPerfil;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        btnExcluirConta = (Button) findViewById(R.id.buttonExcluirConta);

        btnExcluirConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.delete();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(EditarPerfil.this, LoginActivity.class));
                EditarPerfil.this.finish();


            }


        });

        voltaPerfil = (Button) findViewById(R.id.voltaPerfil);
        voltaPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentvoltaPerfil = new Intent(EditarPerfil.this, TelaPerfil.class);
                startActivity(intentvoltaPerfil);
                finish();
            }
        });

    }
}
