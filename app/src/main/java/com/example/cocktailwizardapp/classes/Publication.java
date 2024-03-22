package com.example.cocktailwizardapp.classes;

import android.widget.ImageView;
import android.widget.TextView;

public class Publication{
    private int id_cocktail;
    private String nom;
    private int nb_likes;
    private String date_publication;
    private String saveur;

    //toString
    @Override
    public String toString() {
        return "Publication{" +
                "id_cocktail=" + id_cocktail +
                ", nom='" + nom + '\'' +
                ", nb_likes=" + nb_likes +
                ", date_publication=" + date_publication +
                ", saveur=" + saveur +
                '}';
    }

    //Getters and Setters
    public int getid_cocktail() {
        return id_cocktail;
    }

    public void setid_cocktail(int id_cocktail) {
        this.id_cocktail = id_cocktail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getnb_likes() {
        return nb_likes;
    }

    public void setnb_likes(int nb_likes) {
        this.nb_likes = nb_likes;
    }

    public String getdate_publication() {
        return date_publication;
    }

    public void setdate_publication(String date_publication) {
        this.date_publication = date_publication;
    }

    public String getUmami() {
        return saveur;
    }

    public void setUmami(String umami) {
        this.saveur = umami;
    }

}
/*
[
{"id_cocktail":1,
"nom":"Mojito",
"nb_likes":1,
"date_publication":"2024-03-16",
"umami":"Sucr\u00e9"
},
{"id_cocktail":2,
"nom":"Gin Tonic",
"nb_likes":2,
"date_publication":"2024-03-16",
"umami":"Amer"}
]
 */