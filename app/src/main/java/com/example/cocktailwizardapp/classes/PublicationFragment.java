package com.example.cocktailwizardapp.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.cocktailwizardapp.R;

public class PublicationFragment extends DialogFragment {
    private Publication publication;

    public static PublicationFragment newInstance(Publication pub){
        PublicationFragment fragment = new PublicationFragment();
        Bundle args = new Bundle();
        args.putParcelable("Publication", pub);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onStart() {
        super.onStart();
    getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);

    setCancelable(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fiche_cocktail, container, false);
        ImageView imgCocktail = view.findViewById(R.id.imgCocktailFiche_id);
        TextView nomCocktail = view.findViewById(R.id.nomCocktailFiche_id);
        TextView ingredients = view.findViewById(R.id.ingredientFiche_id);
        TextView ingredientsScroll = view.findViewById(R.id.listeIngredientsFiche_id);
        TextView nbAime = view.findViewById(R.id.nbrAimeFiche_id);
        TextView nomAuteur = view.findViewById(R.id.nomUtilFiche_id);
        TextView titreDesc = view.findViewById(R.id.titreDescFiche_id);
        TextView description = view.findViewById(R.id.descFiche_id);
        TextView titrePreparation = view.findViewById(R.id.titrePrepFiche_id);
        TextView preparation = view.findViewById(R.id.prepFiche_id);

        System.out.println("Dedans onCreateView");


        if (getArguments() != null) {
            publication = getArguments().getParcelable("Publication");
            System.out.println(publication.toString());

            if (publication != null) {
                // Assign values to text fields from the Post object
                nomCocktail.setText(publication.getNom());
                ingredients.setText("Ingredients: ");
                ingredientsScroll.setText(publication.getIngredients().toString());
                nbAime.setText(Integer.toString(publication.getNb_like()));
                nomAuteur.setText(publication.getAuteur());
                titreDesc.setText("Titre Description: ");
                description.setText(publication.getDesc());
                titrePreparation.setText("Titre Preparation: ");
                preparation.setText(publication.getPreparation());
            }
        }

        return view;

    }
}
