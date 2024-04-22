package com.example.cocktailwizardapp.classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class JSONController {
    public ArrayList<Publication> publications;

    public Publication creerPublication(String jsonData){
        Publication pub;

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            pub = objectMapper.readValue(jsonData, Publication.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return pub;
    }

    public ArrayList<Publication> creerPublications(String jsonData, ArrayList<Publication> pubs) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonData, new TypeReference<ArrayList<Publication>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            return pubs;
        }
    }

    public ArrayList<Commentaire> getCommentaires(String jsonData, ArrayList<Commentaire> comments){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if(jsonData == ""){
                comments.add(new Commentaire(1, 0, false,null, null, "Pas de commentaires", null));
                return comments;
            }
            return objectMapper.readValue(jsonData, new TypeReference<ArrayList<Commentaire>>() {});
        }catch (IOException e){
            e.printStackTrace();
            return comments;
        }
    }
}
