package com.example.cocktailwizardapp.vue;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cocktailwizardapp.R;
import com.example.cocktailwizardapp.vue.SelecteurPhoto;

public class MonProfil extends AppCompatActivity implements View.OnClickListener {

    Button btnModMdp, btnDeco, btnSuppCompt;
    ImageView imgProfil,retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_profil);

        btnModMdp = findViewById(R.id.btnModifierMdp_id);
        btnModMdp.setOnClickListener(this);

        btnDeco = findViewById(R.id.btnDeco_id);
        btnDeco.setOnClickListener(this);

        btnSuppCompt = findViewById(R.id.btnSuppCompt_id);
        btnSuppCompt.setOnClickListener(this);

        imgProfil = findViewById(R.id.imgProfil_id);
        imgProfil.setOnClickListener(this);

        retour = findViewById(R.id.retourMP_id);
        retour.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //TODO
        // implementer les fonctions de boutons

        if(v == retour){
            finish();
        }
        else if (v == imgProfil) {
            Intent modifierPhoto = new Intent(this, SelecteurPhoto.class);

        }
    }
}