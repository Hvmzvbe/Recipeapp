package com.example.recipeapp2.View.Plat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.recipeapp2.Model.model.Plat;
import com.example.recipeapp2.Model.model.PlatResponse;
import com.example.recipeapp2.Model.network.RecipeApi;
import com.example.recipeapp2.Model.network.RetrofitInstance;
import com.example.recipeapp2.R;

import java.lang.reflect.Field;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlatDetailActivit extends AppCompatActivity {

    //

    private void fetchDetails(String id) {
        RecipeApi api = RetrofitInstance.getRecipeApi();
        Call<PlatResponse> call = api.getPlatById(id);
        call.enqueue(new Callback<PlatResponse>() {
            @Override
            public void onResponse(Call<PlatResponse> call, Response<PlatResponse> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().getMeals().isEmpty()) {
                    Plat plat = response.body().getMeals().get(0);
                    displayPlatDetails(plat);
                }
            }

            @Override
            public void onFailure(Call<PlatResponse> call, Throwable t) {
                Toast.makeText(PlatDetailActivit.this, "Erreur de chargement", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayPlatDetails(Plat plat) {
        TextView title = findViewById(R.id.platTitle);
        TextView ingredients = findViewById(R.id.platIngredients);
        TextView instructions = findViewById(R.id.platInstructions);
        ImageView image = findViewById(R.id.platImage);

        title.setText(plat.getTitle());
        instructions.setText(plat.getInstructions());

        // Concatène les ingrédients (si non null)
//        StringBuilder ing = new StringBuilder();
//        for (int i = 1; i <= 20; i++) {
//            try {
//                Field field = Plat.class.getDeclaredField("strIngredient" + i);
//                field.setAccessible(true);
//                String value = (String) field.get(plat);
//                if (value != null && !value.isEmpty())
//                    ing.append("• ").append(value).append("\n");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        StringBuilder ingredientList = new StringBuilder();
        if (plat.getIngredient1() != null) ingredientList.append("• ").append(plat.getIngredient1()).append("\n");
        if (plat.getIngredient2() != null) ingredientList.append("• ").append(plat.getIngredient2()).append("\n");
        if (plat.getIngredient3() != null) ingredientList.append("• ").append(plat.getIngredient3()).append("\n");
        if (plat.getIngredient4() != null) ingredientList.append("• ").append(plat.getIngredient4());


        ingredients.setText(ingredientList.toString());
        ingredients.setText(ingredientList.toString());

        Glide.with(this)
                .load(plat.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .into(image);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_plat_detail);
        Plat plat = (Plat) getIntent().getSerializableExtra("plat");
        TextView title = findViewById(R.id.platTitle);
        ImageView image = findViewById(R.id.platImage);
        TextView ingredients = findViewById(R.id.platIngredients);
        TextView description = findViewById(R.id.platInstructions);

        String id = getIntent().getStringExtra("idMeal");
        if (id != null) {
            fetchDetails(id);
        }

        if (plat != null) {
            title.setText(plat.getTitle());

            Glide.with(this)
                    .load(plat.getImageUrl())
                    .placeholder(R.drawable.placeholder)
                    .into(image);


            StringBuilder ingredientList = new StringBuilder();
            if (plat.getIngredient1() != null) ingredientList.append("• ").append(plat.getIngredient1()).append("\n");
            if (plat.getIngredient2() != null) ingredientList.append("• ").append(plat.getIngredient2()).append("\n");
            if (plat.getIngredient3() != null) ingredientList.append("• ").append(plat.getIngredient3()).append("\n");
            if (plat.getIngredient4() != null) ingredientList.append("• ").append(plat.getIngredient4());


            ingredients.setText(ingredientList.toString());
            description.setText(plat.getInstructions());
        }

    }
}