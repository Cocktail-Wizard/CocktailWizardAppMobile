package com.example.cocktailwizardapp.classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONController {

    public String data =
            "{\"id_cocktail\":1,\n" +
            "\"nom\":\"Mojito\",\n" +
            "\"nb_likes\":1,\n" +
            "\"date_publication\":\"2024-03-16\",\n" +
            "\"umami\":\"Sucr\\u00e9\"\n" +
            "}";

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

}
