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
import com.example.cocktailwizardapp.classes.ApiCommunication;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MonProfil extends AppCompatActivity implements View.OnClickListener {

    Button btnModMdp, btnDeco, btnSuppCompt;
    ImageView retour,imgProfil;

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

        imgProfil = findViewById(R.id.imageViewProfil_id);

        SharedPreferences sharedPreferences = getSharedPreferences("infoUtilisateur",MODE_PRIVATE);
        String nomUtilisateur = "@"+sharedPreferences.getString("nom", null);
        String nom = sharedPreferences.getString("nom", null);
        nomUtilTV.setText(nomUtilisateur);

        retour = findViewById(R.id.retourMP_id);
        retour.setOnClickListener(this);

        ApiCommunication apiCommunication = new ApiCommunication();
        apiCommunication.getInfoUtilisateur(nom, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();

                    try {
                        JSONObject jsonObject = new JSONObject(responseBody);
                        final String imageUrl = "https://equipe105.tch099.ovh/images?image="+jsonObject.getString("img_profil");

                        runOnUiThread(() -> {
                            ImageView imageProfil = findViewById(R.id.imageViewProfil_id);
                            Picasso.get().load(imageUrl).resize(500,500).into(imageProfil);
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                        runOnUiThread(() -> Toast.makeText(MonProfil.this, "Erreur: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                    }
                } else {
                    runOnUiThread(() -> Toast.makeText(MonProfil.this, "Erreur inconnue. Essayer à nouveau.", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

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
        } if (v == btnSuppCompt) {
            Dialog suppCpt = new Dialog(this);
            suppCpt.setContentView(R.layout.dialog_supprimer_compte);

            Button btnOui = suppCpt.findViewById(R.id.btnOuiProfil_id);
            Button btnNon = suppCpt.findViewById(R.id.btnNonProfil_id);


            btnOui.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences = getSharedPreferences("infoUtilisateur",MODE_PRIVATE);
                    String nom = sharedPreferences.getString("nom",null);
                    ApiCommunication apiCommunication = new ApiCommunication();
                    apiCommunication.supprimerProfil(nom, new ApiCommunication.ApiCallback() {
                        @Override
                        public void onApiSuccess() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.clear();
                                    editor.apply();
                                    finish();
                                    Toast.makeText(MonProfil.this, ":(.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onApiFailure() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MonProfil.this, "Erreur de communication.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    suppCpt.dismiss();
                }
            });
            btnNon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    suppCpt.dismiss();
                }
            });
            suppCpt.show();
        }
    }
}
