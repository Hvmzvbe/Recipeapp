package com.example.recipeapp2.Model.network;

import com.example.recipeapp2.Model.model.PlatResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApi {
    @GET("search.php")
    Call<PlatResponse> searchRecipes(@Query("s") String query);
    @GET("filter.php")
    Call<PlatResponse> getMealsByCountry(@Query("a") String country);
    @GET("lookup.php")
    Call<PlatResponse> getPlatById(@Query("i") String id);

}
