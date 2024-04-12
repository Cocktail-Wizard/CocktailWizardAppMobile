package com.example.cocktailwizardapp.classes;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
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
    public interface ApiCallback {
        void onApiSuccess();
        void onApiFailure();
    }


    public void ajouterIngredients(String nomIngredient, String username, ApiCallback callback) {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject json = new JSONObject();
        try {
            json.put("nomIngredient", nomIngredient);
            json.put("username", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(JSON, json.toString());
        Request request = new Request.Builder()
                .url(API_URL+"/users/ingredients")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                callback.onApiFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.onApiSuccess();
                } else {
                    callback.onApiFailure();
                }
            }
        });
    }

    public void supprimerProfil(String username, final ApiCallback callback) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject json = new JSONObject();
        try {
            json.put("username", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(JSON, json.toString());
        Request request = new Request.Builder()
                .url(API_URL + "/users")
                .delete(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                callback.onApiFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.onApiSuccess();
                } else {
                    callback.onApiFailure();
                }
            }
        });
    }
}
