package com.example.cocktailwizardapp.adapteur;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailwizardapp.R;
import com.example.cocktailwizardapp.classes.Ingredient;

import java.util.List;

public class IngredientAdapteur extends RecyclerView.Adapter<IngredientAdapteur.IngredientViewHolder> {

    private List<Ingredient> ingredientList;
    public  IngredientAdapteur(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }
    @NonNull
    @Override
    public IngredientAdapteur.IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredient,parent, false);
        return new IngredientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapteur.IngredientViewHolder holder, int position) {
        Ingredient itemIngredient = ingredientList.get(position);
        holder.nomIngredient.setText(itemIngredient.getIngredient());
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public  static class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView nomIngredient;
        public IngredientViewHolder(View itemView) {
            super(itemView);
            nomIngredient = itemView.findViewById(R.id.nomItemIngredient_id);
        }
    }
}
