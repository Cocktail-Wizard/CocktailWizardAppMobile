package com.example.cocktailwizardapp.vue;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.cocktailwizardapp.R;
import com.example.cocktailwizardapp.classes.ConnexionController;
import com.example.cocktailwizardapp.classes.JSONController;
import com.example.cocktailwizardapp.classes.Publication;
import com.example.cocktailwizardapp.classes.PublicationAdapter;
import com.example.cocktailwizardapp.classes.PublicationFragment;
import com.example.cocktailwizardapp.classes.ResponseCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.ArrayList;

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

        barNav.setOnNavigationItemSelectedListener(this);
        barNav.setSelectedItemId(R.id.galerie_id);

        /*==============================================================================*/
        //Initializer le array de publications

        recyclerView = findViewById(R.id.recyclerViewGalerie);
        PublicationAdapter publicationAdapter = new PublicationAdapter(getApplicationContext(),
                new ArrayList<Publication>(),
                new PublicationAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Publication item) {
                        Toast.makeText(getApplicationContext(),
                                "Item Clicked: " + item.getNom(),
                                Toast.LENGTH_LONG).show();


                        PublicationFragment publicationFragment = PublicationFragment.newInstance(item);
                        publicationFragment.show(getSupportFragmentManager(), "PublicationFragment");

                    }
        });
        recyclerView.setAdapter(publicationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        JSONController jc = new JSONController();
        ConnexionController cc = new ConnexionController(jc);

        try {
            cc.envoyerRequeteCocktailTriLike(new ResponseCallback() {
                @Override
                public void onResponseCallback(ArrayList<Publication> pubs) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("Atteint Runnable!");
                            System.out.println("DATA ADAPTER: " + pubs.toString());
                            publicationAdapter.setData(pubs);
                            publicationAdapter.notifyDataSetChanged();
                        }

                    });
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        //Injecter les publications dans le adapter
//        PublicationAdapter publicationAdapter = new PublicationAdapter(this, pubs);
//        recyclerView.setAdapter(publicationAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*==============================================================================*/

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.profile_id) {
            SharedPreferences sharedPreferences = getSharedPreferences("infoUtilisateur",MODE_PRIVATE);
            String nomUtilisateur = sharedPreferences.getString("nom", null);

            // Vérifier si l'utilisateur est déjà connecté
                if(nomUtilisateur == null){
                    startActivity(new Intent(Galerie.this, Connexion.class));
                    return  true;
                }
                startActivity(new Intent(Galerie.this, MonProfil.class));
                return true;
        }
        if(item.getItemId() == R.id.galerie_id) {
            scrollView.smoothScrollTo(0,0);
            return true;
        }
        if(item.getItemId() == R.id.monBar_id) {
            SharedPreferences sharedPreferences = getSharedPreferences("infoUtilisateur",MODE_PRIVATE);
            String nomUtilisateur = sharedPreferences.getString("nom", null);

            // Vérifier si l'utilisateur est déjà connecté
            if(nomUtilisateur == null){
                startActivity(new Intent(Galerie.this, Connexion.class));
                return  true;
            }
            startActivity(new Intent(Galerie.this, MonBar.class));
            return true;
        }
        return false;
    }
}