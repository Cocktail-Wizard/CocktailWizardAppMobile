package com.example.cocktailwizardapp.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cocktailwizardapp.R;

public class SelectionnerImageProfil extends AppCompatActivity implements View.OnClickListener {

    ImageView img1,img2,img3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectionner_image_profil);


        img1 = findViewById(R.id.imageProfil6_id);
        img1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent resultIntent = new Intent();

        resultIntent.putExtra("imageChoisie", R.drawable.ic_calendrier);
        setResult(RESULT_OK, resultIntent);
        finish();
    }


}