package com.example.cocktailwizardapp.classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class JSONController {


    public String data1 = "{\"id_cocktail\":1,\n" +
            "\"nom\":\"Mojito\",\n" +
            "\"nb_likes\":1,\n" +
            "\"date_publication\":\"2024-03-16\",\n" +
            "\"umami\":\"Sucr\\u00e9\"\n" +
            "}\n";
    public String data2 = "[\n" +
            "{\"id_cocktail\":1,\n" +
            "\"nom\":\"Mojito\",\n" +
            "\"nb_likes\":1,\n" +
            "\"date_publication\":\"2024-03-16\",\n" +
            "\"umami\":\"Sucr\\u00e9\"\n" +
            "},\n" +
            "{\"id_cocktail\":2,\n" +
            "\"nom\":\"Gin Tonic\",\n" +
            "\"nb_likes\":2,\n" +
            "\"date_publication\":\"2024-03-16\",\n" +
            "\"umami\":\"Amer\"}\n" +
            "]";

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
}
