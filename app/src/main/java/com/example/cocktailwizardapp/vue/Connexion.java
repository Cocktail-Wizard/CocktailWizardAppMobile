package com.example.cocktailwizardapp.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.cocktailwizardapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    SharedPreferences sharedPreferences;
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

        sharedPreferences = getSharedPreferences("infoUtilisateur", MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        if (v == lienVersInscription) {
            Intent lienInscription = new Intent(this, Inscription.class);
            startActivity(lienInscription);
        } else if (v == btnConnexion) {

            // Vérifier que les champs sont remplis
            if (inputNomCon.getText().toString().isEmpty() || inputMdpCon.getText().toString().isEmpty()) {
                runOnUiThread(() -> Toast.makeText(Connexion.this, "Veuillez remplir les deux sections", Toast.LENGTH_SHORT).show());
                return;
            }
            new Thread(){
                @Override
                public void run(){
                    OkHttpClient client = new OkHttpClient();

                    // Créer un FormBody car l'API traite du FormData
                    FormBody formBody = new FormBody.Builder()
                            .add("nom", inputNomCon.getText().toString())
                            .add("mdp", inputMdpCon.getText().toString())
                            .build();

                    // Créer la requete, Content-Type pour FormData
                    Request request = new Request.Builder()
                            .url(API_URL + "/users/authentification")
                            .post(formBody)
                            .header("Content-Type", "application/x-www-form-urlencoded")
                            .build();

                    try (Response response = client.newCall(request).execute()){
                        if(response.isSuccessful() && response.body() != null){

                            // Traiter la réponse de l'API
                            String responseBody = response.body().string();
                            JSONObject jsonResponse = new JSONObject(responseBody);

                            // Vérifier si le boolean success = true
                            if (jsonResponse.getBoolean("success")) {
                                String token = jsonResponse.getString("token");
                                runOnUiThread(() -> {
                                    Toast.makeText(Connexion.this, "Connexion réussi", Toast.LENGTH_SHORT).show();

                                    // Garder les infos du comptes
                                    SharedPreferences.Editor infoUtilEdit = sharedPreferences.edit();
                                    infoUtilEdit.putString("nom", inputNomCon.getText().toString());
                                    infoUtilEdit.putString("token",token);
                                    infoUtilEdit.apply();

                                    // Retour à la galerie
                                    finish();
                                });
                            } else {
                                // Extraire le message d'erreur du JSONArray
                                JSONArray erreurs = jsonResponse.getJSONArray("errors");
                                String messageErreur = erreurs.getString(0);
                                runOnUiThread(() -> Toast.makeText(Connexion.this, messageErreur, Toast.LENGTH_SHORT).show());
                            }
                        } else {
                            runOnUiThread(() -> Toast.makeText(Connexion.this, "Identifiant ou mot de passe incorrect", Toast.LENGTH_SHORT).show());
                        }
                    } catch (IOException | JSONException e) {
                        runOnUiThread(() -> Toast.makeText(Connexion.this, "Erreur inconnue! Essayez à nouveau.", Toast.LENGTH_SHORT).show());
                    }
                }
            }.start();
        }
    }
}