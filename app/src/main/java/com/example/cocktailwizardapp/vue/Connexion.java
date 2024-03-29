package com.example.cocktailwizardapp.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.cocktailwizardapp.R;

import java.io.IOException;


import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Connexion extends AppCompatActivity implements View.OnClickListener {

    private static final String API_URL = "https://cocktailwizard.azurewebsites.net/api";
    TextView lienVersInscription;
    EditText inputNomCon,inputMdpCon;
    Button btnConnexion;
    String nom,mdp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        lienVersInscription = findViewById(R.id.lienVersInscriptionTv_id);
        lienVersInscription.setOnClickListener(this);

        inputNomCon = findViewById(R.id.inputNomCon_id);
        inputMdpCon = findViewById(R.id.inputMdpCon_id);

        btnConnexion = findViewById(R.id.btnConnexion_id);
        btnConnexion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == lienVersInscription) {
            Intent lienInscription = new Intent(this, Inscription.class);
            startActivity(lienInscription);
        } else if (v == btnConnexion) {
            new Thread(){
                @Override
                public void run(){
                    OkHttpClient client = new OkHttpClient();

                    // Create FormBody with your parameters
                    FormBody formBody = new FormBody.Builder()
                            .add("nom", inputNomCon.getText().toString())
                            .add("mdp", inputMdpCon.getText().toString())
                            .build();

                    Request request = new Request.Builder()
                            .url(API_URL + "/users/authentification")
                            .post(formBody)
                            .header("Content-Type", "application/x-www-form-urlencoded")
                            .build();

                    try (Response response = client.newCall(request).execute()){
                        if(response.isSuccessful() && response.body() != null){
                            String responseBody = response.body().string();
                            runOnUiThread(() -> {
                                Toast.makeText(Connexion.this, "Connexion reussi", Toast.LENGTH_SHORT).show();
                                Intent login = new Intent(Connexion.this, Galerie.class);
                                startActivity(login);
                                finish();
                            });
                        }else {
                            runOnUiThread(() -> Toast.makeText(Connexion.this, "Identifiant ou mot de passe incorrect", Toast.LENGTH_SHORT).show());
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }.start();
        }
    }


}