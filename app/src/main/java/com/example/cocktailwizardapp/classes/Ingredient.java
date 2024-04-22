package com.example.cocktailwizardapp.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable {
    private double quantite;
    private String unite;
    private String ingredient;

    @Override
    public String toString() {
        return ingredient + " " + quantite + " " + unite;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.quantite);
        dest.writeString(this.unite);
        dest.writeString(this.ingredient);
    }

    public void readFromParcel(Parcel source) {
        this.quantite = source.readDouble();
        this.unite = source.readString();
        this.ingredient = source.readString();
    }

    public Ingredient() {
    }

    public  Ingredient(String ingredient) {
        this.ingredient = ingredient;
    }

    protected Ingredient(Parcel in) {
        this.quantite = in.readDouble();
        this.unite = in.readString();
        this.ingredient = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}
