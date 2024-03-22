package com.example.cocktailwizardapp.vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ScrollView;

import com.example.cocktailwizardapp.R;
import com.example.cocktailwizardapp.classes.JSONController;
import com.example.cocktailwizardapp.classes.Publication;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Galerie extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView barNav;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galerie);

        scrollView = (ScrollView) findViewById(R.id.ScrollViewGalerie_id);

        barNav = findViewById(R.id.bottomNav_id);

        barNav.setOnNavigationItemSelectedListener(this);
        barNav.setSelectedItemId(R.id.galerie_id);

        JSONController jc = new JSONController();
        System.out.println("JSONController cree");
        Publication pub = jc.creerPublication(jc.data);
        System.out.println(pub.toString());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.profile_id) {
                // Start Activity1
                startActivity(new Intent(Galerie.this, MonProfil.class));
                return true;
        }

        if(item.getItemId() == R.id.galerie_id) {
            scrollView.smoothScrollTo(0,0);
            return true;
        }
        return false;
    }
}