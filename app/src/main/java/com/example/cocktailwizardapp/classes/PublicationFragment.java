package com.example.cocktailwizardapp.classes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailwizardapp.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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
        ImageView X = view.findViewById(R.id.imageViewX_id);
        TextView nomCocktail = view.findViewById(R.id.nomCocktailFiche_id);
        TextView ingredients = view.findViewById(R.id.ingredientFiche_id);
        TextView ingredientsScroll = view.findViewById(R.id.listeIngredientsFiche_id);
        TextView nbAime = view.findViewById(R.id.nbrAimeFiche_id);
        TextView nomAuteur = view.findViewById(R.id.nomUtilFiche_id);
        TextView titreDesc = view.findViewById(R.id.titreDescFiche_id);
        TextView description = view.findViewById(R.id.descFiche_id);
        TextView titrePreparation = view.findViewById(R.id.titrePrepFiche_id);
        TextView preparation = view.findViewById(R.id.prepFiche_id);
        RecyclerView recyclerCommentaires = view.findViewById(R.id.recyclerViewCommentaires);

        System.out.println("Dedans onCreateView");


        X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        if (getArguments() != null) {
            publication = getArguments().getParcelable("Publication");
            System.out.println(publication.toString());

            if (publication != null) {
                //Ajouter image
                Picasso.get().load(publication.getImg_cocktail()).resize(500,0).into(imgCocktail);

                // Assigner les valeurs texte pour les champs a partir de la Publication
                nomCocktail.setText(publication.getNom());
                ingredients.setText("Ingredients: ");

                String ingredientsString = "";
                for(Ingredient i : publication.getIngredients()){
                    ingredientsString += i.toString() + "\n";
                }
                ingredientsScroll.setText(ingredientsString);
                nbAime.setText(Integer.toString(publication.getNb_like())+"❤️");
                nomAuteur.setText("@"+publication.getAuteur());
                description.setText(publication.getDesc());
                preparation.setText(publication.getPreparation());


                //Ajouter commentaires
                CommentaireAdapter commentaireAdapter = new CommentaireAdapter(getContext().getApplicationContext(),
                                                                    new ArrayList<Commentaire>());
                recyclerCommentaires.setAdapter(commentaireAdapter);
                recyclerCommentaires.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));

                JSONController jc = new JSONController();
                ConnexionController cc = new ConnexionController(jc);

                try {
                    cc.envoyerRequeteGetCommentairesCocktail(new ResponseCallbackComments() {
                        @Override
                        public void onResponseCallback(ArrayList<Commentaire> comments) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.println("Atteint Runnable!");
                                    System.out.println("DATA ADAPTER: " + comments.toString());
                                    commentaireAdapter.setData(comments);
                                    commentaireAdapter.notifyDataSetChanged();
                                }

                            });
                        }
                    }, publication.getId_cocktail());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        return view;

    }
}
