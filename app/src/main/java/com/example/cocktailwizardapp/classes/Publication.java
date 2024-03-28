package com.example.cocktailwizardapp.classes;

import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Blob;
import java.util.Arrays;

public class Publication{
    private int id_cocktail;
    private String nom;
    private String desc;
    private String preparation;
    private String img_cocktail;
    private String img_auteur;
    private String auteur;

    private int nb_like;
    private String date;
    private String alcool_principale;
    private String profil_saveur;
    private String type_verre;
    private boolean liked;
    private Ingredient[] ingredients_cocktail;

    //toString

    @Override
    public String toString() {
        return "Publication{" +
                "id_cocktail=" + id_cocktail +
                ", nom='" + nom + '\'' +
                ", desc='" + desc + '\'' +
                ", preparation='" + preparation + '\'' +
                ", img_cocktail=" + img_cocktail +
                ", img_auteur=" + img_auteur +
                ", auteur='" + auteur + '\'' +
                ", nb_like=" + nb_like +
                ", date='" + date + '\'' +
                ", alcool_principale='" + alcool_principale + '\'' +
                ", profil_saveur='" + profil_saveur + '\'' +
                ", type_verre='" + type_verre + '\'' +
                ", liked=" + liked +
                ", ingredients=" + Arrays.toString(ingredients_cocktail) +
                '}';
    }

    //Getters and Setters

    public int getId_cocktail() {
        return id_cocktail;
    }

    public void setId_cocktail(int id_cocktail) {
        this.id_cocktail = id_cocktail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getImg_cocktail() {
        return img_cocktail;
    }

    public void setImg_cocktail(String img_cocktail) {
        this.img_cocktail = img_cocktail;
    }

    public String getImg_auteur() {
        return img_auteur;
    }

    public void setImg_auteur(String img_auteur) {
        this.img_auteur = img_auteur;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getNb_like() {
        return nb_like;
    }

    public void setNb_like(int nb_like) {
        this.nb_like = nb_like;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAlcool_principale() {
        return alcool_principale;
    }

    public void setAlcool_principale(String alcool_principale) {
        this.alcool_principale = alcool_principale;
    }

    public String getProfil_saveur() {
        return profil_saveur;
    }

    public void setProfil_saveur(String profil_saveur) {
        this.profil_saveur = profil_saveur;
    }

    public String getType_verre() {
        return type_verre;
    }

    public void setType_verre(String type_verre) {
        this.type_verre = type_verre;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public Ingredient[] getIngredients() {
        return ingredients_cocktail;
    }

    public void setIngredients_cocktail(Ingredient[] ingredients) {
        this.ingredients_cocktail = ingredients;
    }
}