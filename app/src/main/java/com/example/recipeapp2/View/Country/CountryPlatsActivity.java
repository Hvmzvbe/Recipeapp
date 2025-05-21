package com.example.recipeapp2.View.Country;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp2.Model.model.Plat;
import com.example.recipeapp2.Model.model.PlatResponse;
import com.example.recipeapp2.Model.network.RecipeApi;
import com.example.recipeapp2.Model.network.RetrofitInstance;
import com.example.recipeapp2.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryPlatsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CountryPlatAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_country_plats);


        recyclerView = findViewById(R.id.country_recycler_view);
        progressBar = findViewById(R.id.progress_bar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String country = getIntent().getStringExtra("country");
        if (country != null) {
            loadPlatsByCountry(country);
        } else {
            Toast.makeText(this, "No country specified", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void loadPlatsByCountry(String country) {
        progressBar.setVisibility(View.VISIBLE);

        RecipeApi api = RetrofitInstance.getRecipeApi();
        Call<PlatResponse> call = api.getMealsByCountry(country);

        call.enqueue(new Callback<PlatResponse>() {
            @Override
            public void onResponse(Call<PlatResponse> call, Response<PlatResponse> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    List<Plat> plats = response.body().getMeals();
                    adapter = new CountryPlatAdapter(plats, CountryPlatsActivity.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(CountryPlatsActivity.this, "No recipes found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PlatResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(CountryPlatsActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}