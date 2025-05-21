package com.example.recipeapp2.View.Home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

//import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipeapp2.Model.model.Plat;
import com.example.recipeapp2.Model.model.PlatResponse;
import com.example.recipeapp2.R;
import com.example.recipeapp2.View.Country.CountryPlatsActivity;
import com.example.recipeapp2.ViewModel.PlatViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {
    EditText searchEditText;
    ListView suggestionList;
    PlatViewModel viewModel;
    ImageView maroc, france , japan;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        Toast.makeText(this, "HomeActivity lancée", Toast.LENGTH_SHORT).show();
//liaison m3a  layout
        searchEditText = findViewById(R.id.searchEditText);
        suggestionList = findViewById(R.id.suggestion_list);
        viewModel = new ViewModelProvider(this).get(PlatViewModel.class);
        maroc = findViewById(R.id.maroc);
        france = findViewById(R.id.france);
        japan = findViewById(R.id.japan);

        //les plat selon pays
        maroc.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, CountryPlatsActivity.class);
            intent.putExtra("country", "Moroccan"); // ou "French", "Japanese" si besoin
            startActivity(intent);
        });

        france.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, CountryPlatsActivity.class);
            intent.putExtra("country", "French");
            startActivity(intent);
        });
        japan.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, CountryPlatsActivity.class);
            intent.putExtra("country", "Japanese");
            startActivity(intent);
        });


        //click hor item liste view sera cacher


//        suggestionList.setOnItemClickListener((parent, view, position, id) -> {
//            Plat selectedPlat = (Plat) parent.getItemAtPosition(position);
//            Toast.makeText(Home.this, selectedPlat.getTitle(), Toast.LENGTH_SHORT).show();
//            suggestionList.setVisibility(View.GONE);
//        });


        //pour tester l’api
        viewModel.fetchRecipesFromApi("t");
// Adapter simple pour afficher les titres
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        suggestionList.setAdapter(adapter);


        // Quand l'utilisateur écrit
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().trim();
                if (!query.isEmpty()) {
//                    suggestionList.setVisibility(View.VISIBLE);
//
//                    viewModel.searchPlats(query).observe(Home.this, plats -> {
//
//                        List<String> titles = new ArrayList<>();
//                        for (Plat plat : plats) {
//                            titles.add(plat.getTitle());
//                        }
//                        Log.d("Search", "Nb plats : " + plats.size());
//                        adapter.clear();
//                        adapter.addAll(titles);
//                        adapter.notifyDataSetChanged();
//                    });



                //serach live
                    viewModel.searchLiveFromApi(query, new Callback<PlatResponse>() {
                        @Override
                        public void onResponse(Call<PlatResponse> call, Response<PlatResponse> response) {
                            if (response.isSuccessful() && response.body() != null && response.body().getMeals() != null) {
                                List<Plat> plats = response.body().getMeals();
                                // Limiter à 5 résultats
                                if (plats.size() > 5) {
                                    plats = plats.subList(0, 5);
                                }


                                SuggestionAdapter adapter = new SuggestionAdapter(Home.this, plats);
                                suggestionList.setAdapter(adapter);
                                suggestionList.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onFailure(Call<PlatResponse> call, Throwable t) {
                            Log.e("API_ERROR", "Erreur API : " + t.getMessage());
                            //si l’utilisateur n a pas Internet
//                            runOnUiThread(() ->
//                                    Toast.makeText(Home.this, "Erreur de connexion à l’API", Toast.LENGTH_SHORT).show()
//                            );
                        }
                    });






                } else {
                    suggestionList.setVisibility(View.GONE);

                }
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
















    }





}