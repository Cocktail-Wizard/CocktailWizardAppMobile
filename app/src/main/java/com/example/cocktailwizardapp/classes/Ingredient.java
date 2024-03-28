package com.example.cocktailwizardapp.classes;

public class Ingredient {
    private double quantite;
    private String unite;
    private String ingredient;

    @Override
    public String toString() {
        return "Ingredient{" +
                "quantite=" + quantite +
                ", unite='" + unite + '\'' +
                ", ingredient='" + ingredient + '\'' +
                '}';
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
}
