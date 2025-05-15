package com.example.recipeapp2.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.recipeapp2.Data.Repository.UserRepository;
import com.example.recipeapp2.Data.model.User;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    public UserViewModel(Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }
    public LiveData<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public void insertUser(User user){
        userRepository.insert(user);
    }
}
