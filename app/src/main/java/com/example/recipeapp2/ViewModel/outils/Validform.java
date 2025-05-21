package com.example.recipeapp2.ViewModel.outils;

import android.util.Patterns;

import com.example.recipeapp2.Model.model.User;

public class Validform {
    //valider le formulaire


    public static String validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) return "Nom d'utilisateur requis";
        return null;
    }

    public static String validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) return "Email requis";
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) return "Email invalide";
        return null;
    }

    public static String validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) return "Mot de passe requis";
        if (password.length() < 6) return "Minimum 6 caractères";
        return null;
    }
    // Pour valider les champs du  SignUp
    public static boolean isSignUpValid(User user) {
        return validateUsername(user.getUsername()) == null &&
                validateEmail(user.getEmail()) == null &&
                validatePassword(user.getPassword()) == null;
    }

    // Pour les champs de Login
    public static boolean isLoginValid(String email, String password) {
        return validateEmail(email) == null &&
                validatePassword(password) == null;
    }


}