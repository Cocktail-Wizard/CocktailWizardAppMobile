package com.example.cocktailwizardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Delais de 3000 millisecondes (chargement apis) avant d'ouvrir page de connexion
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Creer un intention pour aller a Connexion
                Intent intent = new Intent(MainActivity.this, Galerie.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}