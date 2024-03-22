package com.example.cocktailwizardapp.vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.cocktailwizardapp.R;
import com.example.cocktailwizardapp.classes.JSONController;
import com.example.cocktailwizardapp.classes.Publication;
import com.example.cocktailwizardapp.classes.PublicationAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Galerie extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView barNav;
    ScrollView scrollView;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galerie);

        scrollView = (ScrollView) findViewById(R.id.ScrollViewGalerie_id);
        barNav = findViewById(R.id.bottomNav_id);
        recyclerView = findViewById(R.id.recyclerViewGalerie);

        barNav.setOnNavigationItemSelectedListener(this);
        barNav.setSelectedItemId(R.id.galerie_id);



        JSONController jc = new JSONController();
        System.out.println("JSONController cree");

        Publication pub = jc.creerPublication(jc.data1);
        System.out.println(pub.toString());

        ArrayList<Publication> pubs = null;
        pubs = jc.creerPublications(jc.data2, pubs);
        pubs.addAll(pubs);
        for(Publication p : pubs){
            System.out.println(p.toString());
        }

        PublicationAdapter publicationAdapter = new PublicationAdapter(this, pubs);
        recyclerView.setAdapter(publicationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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