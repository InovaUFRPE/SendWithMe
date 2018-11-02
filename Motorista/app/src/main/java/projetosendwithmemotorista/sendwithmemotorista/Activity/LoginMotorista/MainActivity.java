package projetosendwithmemotorista.sendwithmemotorista.Activity.LoginMotorista;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import projetosendwithmemotorista.sendwithmemotorista.R;

public class MainActivity extends AppCompatActivity{

    private Button btnAbrirActivityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarLogin();
            }
        }, 3000);
    }
    private void mostrarLogin() {
        Intent intent = new Intent(MainActivity.this,
                LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

