package com.example.recipeapp2.Model.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.recipeapp2.Model.Dao.Plat.IPlat;
import com.example.recipeapp2.Model.Dao.User.IUser;
import com.example.recipeapp2.Model.model.Plat;
import com.example.recipeapp2.Model.model.User;

@Database(entities = {User.class, Plat.class}, version = 2, exportSchema = false) // ta ndiclariw biha l entité
public abstract class AppDatabase extends RoomDatabase {
    // Singleton instance
    private static AppDatabase instance;

    // Method dyal design pattern singleton de l instance appDatabase
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "recipe_database"
            ).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract IUser getUserDao();
    public abstract IPlat getPlatDao();

}
