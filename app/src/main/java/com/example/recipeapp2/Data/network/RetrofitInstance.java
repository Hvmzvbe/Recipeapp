package com.example.recipeapp2.Data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
//convertir fichier json en objet java
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
//retoutner une instance de l'api
    public static RecipeApi getRecipeApi() {
        return retrofit.create(RecipeApi.class);
    }
}
