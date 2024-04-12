package com.example.cocktailwizardapp.classes;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.sql.Blob;
import java.util.Arrays;

public class Publication implements Parcelable {
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
        this.img_cocktail = "https://equipe105.tch099.ovh/images?image=" + img_cocktail;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id_cocktail);
        dest.writeString(this.nom);
        dest.writeString(this.desc);
        dest.writeString(this.preparation);
        dest.writeString(this.img_cocktail);
        dest.writeString(this.img_auteur);
        dest.writeString(this.auteur);
        dest.writeInt(this.nb_like);
        dest.writeString(this.date);
        dest.writeString(this.alcool_principale);
        dest.writeString(this.profil_saveur);
        dest.writeString(this.type_verre);
        dest.writeByte(this.liked ? (byte) 1 : (byte) 0);
        dest.writeTypedArray(this.ingredients_cocktail, flags);
    }

    public void readFromParcel(Parcel source) {
        this.id_cocktail = source.readInt();
        this.nom = source.readString();
        this.desc = source.readString();
        this.preparation = source.readString();
        this.img_cocktail = source.readString();
        this.img_auteur = source.readString();
        this.auteur = source.readString();
        this.nb_like = source.readInt();
        this.date = source.readString();
        this.alcool_principale = source.readString();
        this.profil_saveur = source.readString();
        this.type_verre = source.readString();
        this.liked = source.readByte() != 0;
        this.ingredients_cocktail = source.createTypedArray(Ingredient.CREATOR);
    }

    public Publication() {
    }

    protected Publication(Parcel in) {
        this.id_cocktail = in.readInt();
        this.nom = in.readString();
        this.desc = in.readString();
        this.preparation = in.readString();
        this.img_cocktail = in.readString();
        this.img_auteur = in.readString();
        this.auteur = in.readString();
        this.nb_like = in.readInt();
        this.date = in.readString();
        this.alcool_principale = in.readString();
        this.profil_saveur = in.readString();
        this.type_verre = in.readString();
        this.liked = in.readByte() != 0;
        this.ingredients_cocktail = in.createTypedArray(Ingredient.CREATOR);
    }

    public static final Creator<Publication> CREATOR = new Creator<Publication>() {
        @Override
        public Publication createFromParcel(Parcel source) {
            return new Publication(source);
        }

        @Override
        public Publication[] newArray(int size) {
            return new Publication[size];
        }
    };
}