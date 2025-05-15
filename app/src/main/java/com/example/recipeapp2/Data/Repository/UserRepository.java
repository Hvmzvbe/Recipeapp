package com.example.recipeapp2.Data.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.recipeapp2.Data.Dao.User.IUser;
import com.example.recipeapp2.Data.Room.AppDatabase;
import com.example.recipeapp2.Data.model.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    private final IUser userDao;
    private final ExecutorService executorService;// bach nexecutiw des operation f background f7al insert

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        userDao = db.getUserDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<User>> getAllUsers() {
                return  userDao.getAll();
    }

    public LiveData<User> findByEmail(String email) {
            return userDao.findByEmail(email);
    }
   public LiveData<User> findByUsername(String username) {
            return userDao.findByUsername(username);
    }
    public void insert(User user) {
        executorService.execute(() -> userDao.insert(user));
    }

    public void delete(User user) {
        executorService.execute(() -> userDao.delete(user));
    }

    public void update(User user) {
        executorService.execute(() -> userDao.update(user));
    }

}
