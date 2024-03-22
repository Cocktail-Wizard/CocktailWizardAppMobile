package com.example.cocktailwizardapp.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailwizardapp.R;

import java.util.ArrayList;
import java.util.List;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Publication> publications;

    public PublicationAdapter(Context context, ArrayList<Publication> publications) {
        this.context = context;
        this.publications = publications;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cocktail_galerie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Publication pub = publications.get(position);

        if((pub.getNom() == null) || (pub.getnb_likes() == 0)){
            return;
        }

        holder.titreTextView.setText(pub.getNom());
        holder.nbLikesTextView.setText(Integer.toString(pub.getnb_likes()));
    }

    @Override
    public int getItemCount() {
        return publications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titreTextView;
        TextView nbLikesTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titreTextView = itemView.findViewById(R.id.nomCocktailGalerie_id);
            nbLikesTextView = itemView.findViewById(R.id.nbAimeCocktailGalerie_id);

        }
    }
}

