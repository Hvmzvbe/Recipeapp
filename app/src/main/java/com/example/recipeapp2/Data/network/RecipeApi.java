package com.example.recipeapp2.Data.network;

import com.example.recipeapp2.Data.model.Plat;
import com.example.recipeapp2.Data.model.PlatResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApi {
    @GET("search.php")
    Call<PlatResponse> searchRecipes(@Query("s") String query);

}
