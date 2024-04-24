package com.example.cocktailwizardapp.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocktailwizardapp.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Inscription extends AppCompatActivity implements View.OnClickListener {

    private static final String API_URL = "https://cocktailwizard.azurewebsites.net/api";
    ImageView calendrier;
    TextView outputDate;
    EditText inputNomIns,inputCourIns,inputMdp,inputConfMdp;
    ImageView retour;
    Button btnInscrire;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        calendrier = findViewById(R.id.imageCalendrier_id);
        calendrier.setOnClickListener(this);

        outputDate = findViewById(R.id.outputDateIns_id);

        inputNomIns = findViewById(R.id.inputNomIns_id);
        inputCourIns = findViewById(R.id.inputCourIns_id);
        inputMdp = findViewById(R.id.inputMdpIns_id);
        inputConfMdp = findViewById(R.id.inputConfMdpIns_id);

        btnInscrire = findViewById(R.id.btnInscrire_id);
        btnInscrire.setOnClickListener(this);

        retour = findViewById(R.id.retourInscription_id);
        retour.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == calendrier) {

            final Calendar calen = Calendar.getInstance();

            int annee = calen.get(Calendar.YEAR);
            int mois = calen.get(Calendar.MONTH);
            int jour = calen.get(Calendar.DAY_OF_MONTH);


            // Creer le DatePicker
            DatePickerDialog selecteurDate = new DatePickerDialog(
                    Inscription.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int annee,
                                      int mois, int jour) {
                    // Modifier le TextView avec les valeurs de date choisi
                    outputDate.setText(annee + "-" + (mois + 1) + "-" + jour);

                }
            }, annee, mois, jour);
            selecteurDate.show();
        } else if (v == btnInscrire) {

            // Vérifier que les champs sont remplis
            if (inputNomIns.getText().toString().isEmpty() || inputCourIns.getText().toString().isEmpty() || inputMdp.getText().toString().isEmpty() || inputConfMdp.getText().toString().isEmpty() || outputDate.getText().toString().isEmpty()) {
                runOnUiThread(() -> Toast.makeText(Inscription.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show());
                return;
            }

            new Thread(){
                @Override
                public void run(){
                    OkHttpClient client = new OkHttpClient();

                    // Créer un FormBody car l'API traite du FormData
                    FormBody formBody = new FormBody.Builder()
                            .add("nom", inputNomIns.getText().toString())
                            .add("courriel", inputCourIns.getText().toString())
                            .add("mdp", inputMdp.getText().toString())
                            .add("confmdp", inputConfMdp.getText().toString())
                            .add("naissance", outputDate.getText().toString())
                            .build();

                    // Créer la requete, Content-Type pour FormData
                    Request request = new Request.Builder()
                            .url(API_URL + "/users")
                            .post(formBody)
                            .header("Content-Type", "application/x-www-form-urlencoded")
                            .build();

                    try (Response response = client.newCall(request).execute()){
                        if(response.isSuccessful() && response.body() != null){

                            // Traiter la réponse de l'API (UTF_8 car la réponse contient un accent)
                            String responseBody = response.body().source().readString(StandardCharsets.UTF_8);
                            responseBody = responseBody.replace("\\u00e9", "é");
                            // Si réponse Inscription réussie!
                            if (responseBody.equals("\"Inscription réussie!\"")) {
                                runOnUiThread(() -> {
                                    Toast.makeText(Inscription.this, "Inscription réussi", Toast.LENGTH_SHORT).show();
                                    // Retour a la page de connexion
                                    finish();
                                });
                            } else {
                                runOnUiThread(() -> Toast.makeText(Inscription.this, "Erreur inconnue! Essayez à nouveau.", Toast.LENGTH_SHORT).show());

                            }
                        } else {
                            // Extraire le array d'erreur du JSONArray envoyer par l'API
                            String responseBody = response.body().string();
                            JSONArray erreurs = new JSONArray(responseBody);

                            // Extraire le message d'erreur du JSONArray
                            String messageErreur = erreurs.getString(0);
                            runOnUiThread(() -> Toast.makeText(Inscription.this, messageErreur, Toast.LENGTH_SHORT).show());
                        }
                    } catch (IOException | JSONException e) {
                        runOnUiThread(() -> Toast.makeText(Inscription.this, "Erreur : "+e.getMessage(), Toast.LENGTH_SHORT).show());
                    }
                }
            }.start();
        }
        if (v == retour){
            finish();
        }
    }
}