package projetosendwithmemotorista.sendwithmemotorista.Activity.Carona;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import projetosendwithmemotorista.sendwithmemotorista.Activity.TelaPrincipal.PrincipalActivity2;
import projetosendwithmemotorista.sendwithmemotorista.R;

public class Novacarona extends AppCompatActivity {

    private Button buttonvoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novacarona);
        buttonvoltar = (Button) findViewById(R.id.button6);
        buttonvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar();
            }
        });

    }
    public void voltar(){
        startActivity(new Intent(Novacarona.this, PrincipalActivity2.class));
    }
}
