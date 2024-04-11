package com.example.cocktailwizardapp.classes;

import com.example.cocktailwizardapp.vue.MonBar;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiCommunication {
    private OkHttpClient client;
    private static final String API_URL = "https://cocktailwizard.azurewebsites.net/api";

    public ApiCommunication() {
        client = new OkHttpClient();
    }

    public void connexion(String nom, String mdp, Callback callback) {
        RequestBody formBody = new FormBody.Builder()
                .add("nom_utilisateur", nom)
                .add("mdp", mdp)
                .build();
        Request request = new Request.Builder()
                .url(API_URL + "/connexion")
                .post(formBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void inscription(String nom, String courriel, String mdp, String naiss, Callback callback) {
        RequestBody formBody = new FormBody.Builder()
                .add("nom_utilisateur", nom)
                .add("mdp", mdp)
                .add("courriel", courriel)
                .add("naiss",naiss)

                .build();
        Request request = new Request.Builder()
                .url(API_URL + "/inscription")
                .post(formBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
