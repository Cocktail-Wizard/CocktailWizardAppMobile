package com.example.cocktailwizardapp.adapteur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailwizardapp.R;
import com.example.cocktailwizardapp.classes.Ingredient;

import java.util.List;
import java.util.Set;

public class IngredientAdapteur extends RecyclerView.Adapter<IngredientAdapteur.IngredientViewHolder> {

    private List<Ingredient> ingredientList;
    private Context context;
    private LinearLayout linearLayout;
    private Set<String> ingredientAjouter;

    public interface OnIngredientClickListener {
        void onIngredientClick(Ingredient ingredient);
    }

    private OnIngredientClickListener clickListener;
    public  IngredientAdapteur(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public IngredientAdapteur(List<Ingredient> ingredients, Context context, LinearLayout linearLayout, OnIngredientClickListener clickListener) {
        this.ingredientList = ingredients;
        this.context = context;
        this.linearLayout = linearLayout;
        this.clickListener = clickListener;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onIngredientClick(itemIngredient);
            }
        });
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
