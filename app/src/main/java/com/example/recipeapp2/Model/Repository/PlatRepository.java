package com.example.recipeapp2.Model.Repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recipeapp2.Model.Dao.Plat.IPlat;
import com.example.recipeapp2.Model.Room.AppDatabase;
import com.example.recipeapp2.Model.model.Plat;
import com.example.recipeapp2.Model.model.PlatResponse;
import com.example.recipeapp2.Model.network.RecipeApi;
import com.example.recipeapp2.Model.network.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlatRepository {
    private final IPlat platDao;
    private final Executor executor;

    public PlatRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);// ta nricupiriw l instance
        platDao = db.getPlatDao();// n9dro nkhdmo les methode (insert , delete ,..) dyal platDao
        executor = Executors.newSingleThreadExecutor(); // n executiw les requetes fi thread wa7ed hors le thread principal
    }

    public LiveData<List<Plat>> searchPlats(String query) {
        return platDao.searchPlats(query);
    }

//  Lire tous les plats
    public LiveData<List<Plat>> getAllPlats() {
        return platDao.getAllPlats();
    }
    //  Lire 10 plats
    public LiveData<List<Plat>> getTenPlats() {
        return platDao.get10Plats();
    }

    //  Insérer un plat
    public void insert(Plat plat) {
        executor.execute(() -> platDao.insertPlat(plat));
    }

    //  Insérer plusieurs plats (ex: via API)


    public void insertAll(List<Plat> plats) {
        executor.execute(() -> platDao.insertAll(plats));
    }

    //  Supprimer un plat
    public void delete(Plat plat) {
        executor.execute(() -> platDao.deletePlat(plat));
    }

    //ta nricupiriw les plats mn api ou n insiriw f room


    public void fetchAndInsertPlats(String query ) {
        RecipeApi recipeApi = RetrofitInstance.getRecipeApi();
        Call<PlatResponse> call = recipeApi.searchRecipes(query);
        call.enqueue(new Callback<PlatResponse>() {
            @Override
            public void onResponse(Call<PlatResponse> call, Response<PlatResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Plat> plats = response.body().getMeals();
                    insertAll(plats); // Insérer les plats dans Room

                }
            }

            @Override
            public void onFailure(Call<PlatResponse> call, Throwable t) {

            }
        });










    }

//api live
    public void searchFromApi(String query, Callback<PlatResponse> callback) {
        RecipeApi api = RetrofitInstance.getRecipeApi();
        Call<PlatResponse> call = api.searchRecipes(query);
        call.enqueue(callback);
    }




}