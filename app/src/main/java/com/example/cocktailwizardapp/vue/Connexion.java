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
import com.example.cocktailwizardapp.classes.ApiCommunication;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Connexion extends AppCompatActivity implements View.OnClickListener {

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
        //TODO
        // Ajouter le onClickListener et verification des donnees
    }

    @Override
    public void onClick(View v) {

        if(v == lienVersInscription){
            Intent lienInscription = new Intent(this, Inscription.class);
            startActivity(lienInscription);
        } else if (v == btnConnexion) {
            ApiCommunication conn = new ApiCommunication();
            Callback callback = new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //TODO
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //TODO
                }
            };
            nom = inputNomCon.getText().toString();
            mdp = inputMdpCon.getText().toString();
            conn.connexion(nom,mdp,callback);

            //TODO
            // SharedPreference pour maintenire la connection
            // If reponse succes!
            /*if(good){
            Intent galerie = new Intent(this, Galerie.class);
            startActivity(galerie);
            }
            else{
            Toast.makeText(this, "Nom ou mot de passe invalide", Toast.LENGTH_SHORT).show();
            }
             */
        }
    }
}