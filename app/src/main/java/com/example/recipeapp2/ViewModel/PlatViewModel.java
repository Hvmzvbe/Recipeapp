package com.example.recipeapp2.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.recipeapp2.Data.Repository.PlatRepository;
import com.example.recipeapp2.Data.model.Plat;
import com.example.recipeapp2.Data.model.PlatResponse;

import java.util.List;

import retrofit2.Callback;


public class PlatViewModel extends AndroidViewModel {
    private final PlatRepository repository;
    private final LiveData<List<Plat>> allPlats;


    public PlatViewModel(@NonNull  Application application) {
        super(application);
        repository = new PlatRepository(application);
        allPlats = repository.getAllPlats(); // Room observer
    }

    public LiveData<List<Plat>> searchPlats(String query) {
        return repository.searchPlats(query);
    }

    public LiveData<List<Plat>> getAllPlats() {
        return allPlats;
    }

    //  Appelle Retrofit pour aller chercher depuis l'API
    public void fetchRecipesFromApi(String query) {
        repository.fetchAndInsertPlats(query);
    }

    public void searchLiveFromApi(String query, Callback<PlatResponse> callback) {
        repository.searchFromApi(query, callback);
    }

}
