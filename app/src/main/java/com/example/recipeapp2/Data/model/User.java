package com.example.recipeapp2.Data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
@Entity(tableName = "User")

public class User {
    @NonNull @PrimaryKey
    private String username;
    @Email
    private String email;
    @NonNull
    @Length(min = 6)
    private String password;


    public User() {
    }
    @Ignore //eviter l ambiguité
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
