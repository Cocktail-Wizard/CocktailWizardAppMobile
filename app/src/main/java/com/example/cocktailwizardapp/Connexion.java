package com.example.cocktailwizardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
;
public class Connexion extends AppCompatActivity implements View.OnClickListener {

    TextView lienVersInscription;
    EditText inputNomCon,inputMdpCon;
    Button btnConnexion;
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
        }
    }
}