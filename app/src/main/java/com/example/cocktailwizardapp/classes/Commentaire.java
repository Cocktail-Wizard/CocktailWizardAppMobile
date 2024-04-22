package com.example.cocktailwizardapp.classes;

public class Commentaire {
    private int id_commentaire;
    private int nb_like;
    private boolean liked;
    private String img_auteur;
    private String auteur;
    private String contenu;
    private String date;

    public Commentaire(int id_commentaire, int nb_like, boolean liked, String img_auteur, String auteur, String contenu, String date) {
        this.id_commentaire = id_commentaire;
        this.nb_like = nb_like;
        this.liked = liked;
        this.img_auteur = img_auteur;
        this.auteur = auteur;
        this.contenu = contenu;
        this.date = date;
    }

    public Commentaire(){
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "id_commentaire=" + id_commentaire +
                ", nb_like=" + nb_like +
                ", liked=" + liked +
                ", img_auteur='" + img_auteur + '\'' +
                ", auteur='" + auteur + '\'' +
                ", contenu='" + contenu + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public int getid_commentaire() {
        return id_commentaire;
    }

    public void setid_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public int getNb_like() {
        return nb_like;
    }

    public void setNb_like(int nb_like) {
        this.nb_like = nb_like;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
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

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
