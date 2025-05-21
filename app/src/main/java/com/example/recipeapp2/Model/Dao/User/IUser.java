package com.example.recipeapp2.Model.Dao.User;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.recipeapp2.Model.model.User;

import java.util.List;

@Dao
public interface IUser {
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAll();



    @Query("SELECT * FROM User WHERE username = :username")
    LiveData<User> findByUsername(String username);

    @Query("SELECT * FROM User WHERE email = :email")
    LiveData<User> findByEmail(String email);

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM User WHERE username = :username")
    void deleteByUsername(String username);

}
