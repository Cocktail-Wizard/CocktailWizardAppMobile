package com.example.cocktailwizardapp.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocktailwizardapp.R;

public class MonProfil extends AppCompatActivity implements View.OnClickListener {

    Button btnModMdp, btnDeco, btnSuppCompt;
    ImageView retour;

    TextView nomUtilTV;

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

        nomUtilTV = findViewById(R.id.nomUtilProfil_id);

        SharedPreferences sharedPreferences = getSharedPreferences("infoUtilisateur",MODE_PRIVATE);
        String nomUtilisateur = "@"+sharedPreferences.getString("nom", null);

        nomUtilTV.setText(nomUtilisateur);

        retour = findViewById(R.id.retourMP_id);
        retour.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //TODO
        // implementer les fonctions de boutons
        if(v == retour){
            finish();
        } if (v == btnModMdp) {
            Intent modMdp = new Intent(this,ModifierMotDePasse.class);
            startActivity(modMdp);
        } if (v == btnDeco) {
            Dialog deco = new Dialog(this);
            deco.setContentView(R.layout.dialog_deco);

            // Chercher les boutons de la page modale
            Button btnOui = deco.findViewById(R.id.btnDecoOui_id);
            Button btnNon = deco.findViewById(R.id.btnDecoNon_id);


            // OnCLickListener btnOui
            btnOui.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Remettre à null la valeur du SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("infoUtilisateur", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();

                    Toast.makeText(MonProfil.this, "Bye bye!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

            // OnCLickListener btnNon
            btnNon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deco.dismiss();
                }
            });
            deco.show();
        }
    }
}
