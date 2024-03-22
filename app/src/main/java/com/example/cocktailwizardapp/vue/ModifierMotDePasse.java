package com.example.cocktailwizardapp.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cocktailwizardapp.R;

public class ModifierMotDePasse extends AppCompatActivity implements View.OnClickListener{

    ImageView imgRetourProfil;
    EditText inputMdpActuel, inputMdpNouveau, inputMdpConf;
    Button btnConf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_mot_de_passe);
        
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
            //TODO

        }
    }
}