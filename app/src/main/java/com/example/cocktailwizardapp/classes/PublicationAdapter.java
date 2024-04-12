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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Publication> publications;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Publication item);
    }

    public PublicationAdapter(Context context, ArrayList<Publication> publications, OnItemClickListener listener) {
        this.context = context;
        this.publications = publications;
        this.listener = listener;
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

        if(pub.getNom() == null){
            return;
        }

        holder.titreTextView.setText(pub.getNom());
        holder.nbLikesTextView.setText(Integer.toString(pub.getNb_like()));

        //Ajouter image a l'affichage
        Picasso.get().load(pub.getImg_cocktail()).resize(500, 0).into(holder.imgCocktail);

        holder.bind(publications.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return publications.size();
    }

    public void setData(ArrayList<Publication> pubs) {
        this.publications = pubs;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titreTextView;
        TextView nbLikesTextView;
        ImageView imgCocktail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titreTextView = itemView.findViewById(R.id.nomCocktailGalerie_id);
            nbLikesTextView = itemView.findViewById(R.id.nbAimeCocktailGalerie_id);
            imgCocktail = itemView.findViewById(R.id.imgCocktailGalerie_id);

        }

        public void bind(final Publication item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}

