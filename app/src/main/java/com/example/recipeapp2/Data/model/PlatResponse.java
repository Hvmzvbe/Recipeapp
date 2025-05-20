package com.example.recipeapp2.Data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PlatResponse {

    @SerializedName("meals")
    private List<Plat> meals;

    public List<Plat> getMeals() {
        return meals;
    }

    public void setMeals(List<Plat> meals) {
        this.meals = meals;
    }
}
