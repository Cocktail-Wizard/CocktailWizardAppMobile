package com.example.cocktailwizardapp.vue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.example.cocktailwizardapp.classes.ApiCommunication;
import com.example.cocktailwizardapp.classes.Ingredient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MonBar extends AppCompatActivity implements View.OnClickListener{
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

    View.OnClickListener ingredientButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Button clickedButton = (Button) v;
            String ingredient = clickedButton.getText().toString();

            SharedPreferences sharedPreferences = getSharedPreferences("infoUtilisateur",MODE_PRIVATE);
            String nomUtilisateur = sharedPreferences.getString("nom", null);
            new Thread(() -> {
                OkHttpClient client = new OkHttpClient();
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("nomIngredient", ingredient);
                    jsonObject.put("username",nomUtilisateur );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                Request request = new Request.Builder()
                        .url(API_URL + "/users/ingredients")
                        .delete(body)
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful() && response.body() != null) {

                        // Traiter la réponse de l'API
                        String responseBody = response.body().string();
                        JSONArray jsonArray = new JSONArray(responseBody);
                        List<String> ingredients = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            ingredients.add(jsonArray.getString(i));
                        }

                        // Vérifier si l'ingrédient à été supprimé
                        if (!ingredients.contains(ingredient)) {
                            Log.d("MonBar", "retrait");
                            // Retirer le button correspondant à l'ingrédient
                            runOnUiThread(() -> {
                                LinearLayout linearLayout = findViewById(R.id.monBarLL_id);
                                linearLayout.removeView(v);
                                Toast.makeText(MonBar.this, "Ingrédient retiré avec succès!", Toast.LENGTH_SHORT).show();
                            });
                        } else {
                            runOnUiThread(() -> Toast.makeText(MonBar.this, "Erreur retrait de l'ingrédient.", Toast.LENGTH_SHORT).show());
                        }
                    } else {
                        runOnUiThread(() -> Toast.makeText(MonBar.this, "Erreur inconnue! Essayez à nouveau.", Toast.LENGTH_SHORT).show());
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    };
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
                                    IngredientAdapteur adapter = new IngredientAdapteur(ingredients, MonBar.this, linearLayout, new IngredientAdapteur.OnIngredientClickListener() {
                                        @Override
                                        public void onIngredientClick(Ingredient ingredient) {
                                            ApiCommunication apiCommunication = new ApiCommunication();
                                            SharedPreferences sharedPreferences = getSharedPreferences("infoUtilisateur",MODE_PRIVATE);
                                            String nomUtilisateur = sharedPreferences.getString("nom", null);
                                            apiCommunication.ajouterIngredients(ingredient.getIngredient(), nomUtilisateur, new ApiCommunication.ApiCallback() {
                                                @Override
                                                public void onApiSuccess() {
                                                    runOnUiThread(() -> {
                                                        LinearLayout linearLayout = findViewById(R.id.monBarLL_id);

                                                        Button button = new Button(MonBar.this);
                                                        button.setText(ingredient.getIngredient());
                                                        linearLayout.addView(button);
                                                        Toast.makeText(MonBar.this, "Ingrédient ajouté.", Toast.LENGTH_SHORT).show();
                                                    });
                                                }

                                                @Override
                                                public void onApiFailure() {
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toast.makeText(MonBar.this, "Ingrédient déjà présent dans la liste.", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    });
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
                                    button.setOnClickListener(ingredientButtonListener);
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