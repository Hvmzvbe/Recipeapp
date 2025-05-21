package com.example.recipeapp2.Model.Dao.Plat;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.recipeapp2.Model.model.Plat;

import java.util.List;

@Dao
public interface IPlat {

    @Query("SELECT * FROM Plat WHERE title LIKE '%' || LOWER(:query)|| '%' LIMIT 5")
    LiveData<List<Plat>> searchPlats(String query);

    @Query("SELECT * FROM Plat")
    LiveData<List<Plat>> getAllPlats();
    @Query("SELECT * FROM Plat WHERE idMeal = :id")
    LiveData<Plat> getPlatById(int id);
    @Query("SELECT * FROM Plat WHERE title = :title")
    LiveData<Plat> getPlatByTitle(String title);

    @Query("SELECT * FROM Plat LIMIT 10")
    LiveData<List<Plat>> get10Plats();

    @Insert(onConflict = OnConflictStrategy.REPLACE) // la kan 3ndna nffss clé primaire bach matrach un exception
    void insertPlat(Plat plat);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Plat> plats);

    @Delete
    void deletePlat(Plat plat);

}
