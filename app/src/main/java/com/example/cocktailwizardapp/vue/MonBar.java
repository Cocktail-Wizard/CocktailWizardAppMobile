package com.example.cocktailwizardapp.vue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocktailwizardapp.R;
import com.example.cocktailwizardapp.adapteur.IngredientAdapteur;
import com.example.cocktailwizardapp.classes.Ingredient;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MonBar extends AppCompatActivity implements View.OnClickListener {
    private static final String API_URL = "https://cocktailwizard.azurewebsites.net/api";
    ImageView btnRetour;
    TextView chercherIngredient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_bar);

        btnRetour = findViewById(R.id.flecheRetourBar_id);
        btnRetour.setOnClickListener(this);

        chercherIngredient = findViewById(R.id.chercherIngredient_id);
        chercherIngredient.setOnClickListener(this);

        getIngredientsUtilisateur();
    }

    @Override
    public void onClick(View v) {
        if(v == btnRetour) {
            finish();
        }
        if(v == chercherIngredient) {

            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_spinner_monbar);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


            EditText searchbar = dialog.findViewById(R.id.inputChercherIngredient_id);

            LinearLayout linearLayout = findViewById(R.id.monBarLL_id);

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(API_URL + "/ingredients")
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MonBar.this, "Erreur :" + e, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        final String resultatOnResponse = response.body().string();
                        MonBar.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONArray jsonArray = new JSONArray(resultatOnResponse);
                                    List<Ingredient> ingredients = new ArrayList<>();
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        String ingredientName = jsonArray.getString(i);
                                        ingredients.add(new Ingredient(ingredientName));
                                    }
                                    // Créer un adapteur et l'ajouter au RecyclerView
                                    RecyclerView recyclerView = dialog.findViewById(R.id.monbarRecyler_id);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(MonBar.this));
                                    IngredientAdapteur adapter = new IngredientAdapteur(ingredients, MonBar.this, linearLayout);
                                    recyclerView.setAdapter(adapter);

                                    dialog.show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    private void getIngredientsUtilisateur() {
        OkHttpClient client = new OkHttpClient();

        SharedPreferences sharedPreferences = getSharedPreferences("infoUtilisateur",MODE_PRIVATE);
        String nomUtilisateur = sharedPreferences.getString("nom", null);

        Request request = new Request.Builder()
                .url(API_URL + "/users/" + nomUtilisateur + "/ingredients")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(MonBar.this, "Erreur :" + e, Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();

                    try {
                        JSONArray ingredients = new JSONArray(responseBody);

                        runOnUiThread(() -> {
                            LinearLayout linearLayout = findViewById(R.id.monBarLL_id);

                            for (int i = 0; i < ingredients.length(); i++) {
                                try {
                                    String ingredient = ingredients.getString(i);

                                    Button button = new Button(MonBar.this);
                                    button.setText(ingredient);
                                    linearLayout.addView(button);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                        runOnUiThread(() -> Toast.makeText(MonBar.this, "Erreur : " + e.getMessage(), Toast.LENGTH_SHORT).show());
                    }
                } else {
                    runOnUiThread(() -> Toast.makeText(MonBar.this, "Erreur inconnue! Essayez à nouveau.", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }
}