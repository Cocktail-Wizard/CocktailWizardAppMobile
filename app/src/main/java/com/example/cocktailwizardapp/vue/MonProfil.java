package com.example.cocktailwizardapp.vue;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cocktailwizardapp.R;

public class MonProfil extends AppCompatActivity implements View.OnClickListener {

    Button btnModMdp, btnDeco, btnSuppCompt;
    ImageView imgProfil,retour;

    int imageChoisie;
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
        } else if (v == btnModMdp) {
            Intent modMdp = new Intent(this,ModifierMotDePasse.class);
            startActivity(modMdp);
        } else if (v == imgProfil) {
            Intent modPfp = new Intent(this, SelectionnerImageProfil.class);
            startActivityForResult(modPfp,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            int imageChoisie = data.getIntExtra("imageChoisie", 0);
            // Now you can use the selected image data
            imgProfil.setImageResource(imageChoisie);
        }
    }
}