package com.example.cocktailwizardapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Galerie extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView barNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galerie);

        barNav = findViewById(R.id.bottomNav_id);

        barNav.setOnNavigationItemSelectedListener(this);
        barNav.setSelectedItemId(R.id.galerie_id);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.profile_id) {
                // Start Activity1
                startActivity(new Intent(Galerie.this, MonProfil.class));
                return true;
        }
        return false;
    }
}