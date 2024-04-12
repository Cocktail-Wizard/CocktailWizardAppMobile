package com.example.cocktailwizardapp.classes;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ConnexionController {
    public String url= "https://reqres.in/api/users/2";
    public String url2 = "https://cocktailwizard.azurewebsites.net/api/cocktails/tri/like";
    public String url3 = "https://cocktailwizard.azurewebsites.net/api/cocktails/%s/commentaires";

    public JSONController jc;

    public ConnexionController(JSONController jc) {
        this.jc = jc;
    }

    public void envoyerRequeteTest() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                System.out.println(myResponse);
                response.body().close();
            }
        });
    }

    public void envoyerRequeteCocktailTriLike(final ResponseCallback callback) throws IOException{
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url2)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                System.out.println("HTTP TEST COCKTAIL WIZRD:::" + myResponse);
                ArrayList<Publication> pubs = null;
                pubs = jc.creerPublications(myResponse, pubs);
                System.out.println("Apres jc 1");
                try{
                    System.out.println("Apres jc 2 :::: =====" + pubs.toString());
                } catch (Exception e){
                    System.out.println("Exception: Unable to transform response to String response invalid.");
                }
                callback.onResponseCallback(pubs);
                response.body().close();
            }
        });
    }

    public void envoyerRequeteGetCommentairesCocktail(final ResponseCallbackComments callback, int id_cocktail) throws IOException{
        OkHttpClient client = new OkHttpClient();

        String requestUrl = String.format(url3, id_cocktail);
        System.out.println("Formatted string: " + requestUrl );

        Request request = new Request.Builder()
                .url(requestUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                System.out.println("HTTP TEST COMMENTAIRES WIZRD:::" + myResponse);
                ArrayList<Commentaire> comments = new ArrayList<Commentaire>();
                comments = jc.getCommentaires(myResponse, comments);
                try{
                    System.out.println("Apres jc commentaire :::: =====" + comments.toString());
                } catch (Exception e){
                    System.out.println("Exception: Unable to transform response to String response invalid.");
                }
                callback.onResponseCallback(comments);
                response.body().close();
            }
        });
    }
}
