package com.example.emano.sendwithme.HomePackage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.HomePackage.Login;

public class Splashscreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarLogin();
            }
        }, 3000);
    }
    private void mostrarLogin() {
        Intent intent = new Intent(Splashscreen.this,
                Login.class);
        startActivity(intent);
        finish();
    }
}