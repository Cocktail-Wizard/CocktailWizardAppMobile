package com.example.cocktailwizardapp.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailwizardapp.R;

import java.util.ArrayList;
import java.util.List;

public class CommentaireAdapter extends RecyclerView.Adapter<CommentaireAdapter.CommentaireViewHolder> {

    private ArrayList<Commentaire> commentaires;
    private Context context;

    public CommentaireAdapter(Context context, ArrayList<Commentaire> commentaireList) {
        this.context = context;
        this.commentaires = commentaireList;
    }

    @NonNull
    @Override
    public CommentaireViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commentaire_item, parent, false);
        return new CommentaireViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentaireViewHolder holder, int position) {
        Commentaire commentaire = commentaires.get(position);
        holder.bind(commentaire);
    }
    public void setData(ArrayList<Commentaire> comments) {
        this.commentaires = comments;
    }

    @Override
    public int getItemCount() {
        return commentaires.size();
    }

    public class CommentaireViewHolder extends RecyclerView.ViewHolder {

        private TextView usernameTextView;
        private TextView commentTextView;

        public CommentaireViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.auteurCommentaire);
            commentTextView = itemView.findViewById(R.id.contenuCommentaire);
        }

        public void bind(Commentaire commentaire) {
            usernameTextView.setText(commentaire.getAuteur());
            commentTextView.setText(commentaire.getContenu());
        }
    }
}
