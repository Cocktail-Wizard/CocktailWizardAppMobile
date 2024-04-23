package com.example.cocktailwizardapp.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cocktailwizardapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ModifierMotDePasse extends AppCompatActivity implements View.OnClickListener{

    ImageView imgRetourProfil;
    EditText inputMdpActuel, inputMdpNouveau, inputMdpConf;
    Button btnConf;
    SharedPreferences sharedPreferences;
    private static final String API_URL = "https://cocktailwizard.azurewebsites.net";
    String nomUtilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_mot_de_passe);

        sharedPreferences = getSharedPreferences("infoUtilisateur",MODE_PRIVATE);
        nomUtilisateur = sharedPreferences.getString("nom", null);

        imgRetourProfil = findViewById(R.id.imgRetourProfil_id);
        imgRetourProfil.setOnClickListener(this);
        
        inputMdpActuel = findViewById(R.id.inputMdpActuel_id);
        inputMdpNouveau = findViewById(R.id.inputNouveauMdp_id);
        inputMdpConf = findViewById(R.id.inputConfiMdpMod_id);
        
        btnConf = findViewById(R.id.btnConfMdpMod_id);
        btnConf.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        
        if (v == imgRetourProfil){
            finish();
        } else if (v == btnConf) {
            // Vérifier que les champs sont remplis
            if (inputMdpActuel.getText().toString().isEmpty() || inputMdpNouveau.getText().toString().isEmpty() || inputMdpConf.getText().toString().isEmpty()) {
                runOnUiThread(() -> Toast.makeText(ModifierMotDePasse.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show());
                return;
            }

            new Thread() {
                @Override
                public void run(){
                    OkHttpClient client = new OkHttpClient();


                    // Créer un JSONObject
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("nom", nomUtilisateur);
                        jsonObject.put("mdp", inputMdpActuel.getText().toString());
                        jsonObject.put("nouveauMdp", inputMdpNouveau.getText().toString());
                        jsonObject.put("confNouveauMdp", inputMdpConf.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String token = sharedPreferences.getString("token", null);

                    // Créer la requete
                    RequestBody requestBody =  RequestBody.create(
                            MediaType.parse("application/json; charset=utf-8"),
                            jsonObject.toString()
                    );

                    Request request = new Request.Builder()
                            .url(API_URL+"/api/users")
                            .addHeader("Auth",token)
                            .patch(requestBody)
                            .build();

                    try (Response response = client.newCall(request).execute()) {
                        if(response.isSuccessful() && response.body() != null) {

                            // Traiter la réponse de l'API
                            String responseBody = response.body().string();
                            JSONObject jsonResponse = new JSONObject(responseBody);

                            // Vérifier si le boolean success = true
                            if (jsonResponse.getBoolean("success")) {
                                runOnUiThread(() -> {
                                    Toast.makeText(ModifierMotDePasse.this, "Votre mot de passe a été modifié avec succès!", Toast.LENGTH_SHORT).show();
                                });
                            } else {
                                // Extraire le message d'erreur du JSONArray
                                JSONArray erreurs = jsonResponse.getJSONArray("erreurs");
                                String messageErreur = erreurs.getString(0);
                                runOnUiThread(() -> Toast.makeText(ModifierMotDePasse.this, messageErreur, Toast.LENGTH_SHORT).show());
                            }
                        } else {
                            runOnUiThread(() -> Toast.makeText(ModifierMotDePasse.this, "Erreur inconnue! Essayez à nouveau.", Toast.LENGTH_SHORT).show());
                        }
                    } catch (IOException | JSONException e) {
                        runOnUiThread(() -> Toast.makeText(ModifierMotDePasse.this, "Erreur : "+e.getMessage(), Toast.LENGTH_SHORT).show());
                    }
                }
            }.start();
        }
    }
}