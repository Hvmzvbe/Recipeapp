package com.example.recipeapp2.View.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipeapp2.R;
import com.example.recipeapp2.View.Home.Home;
import com.example.recipeapp2.View.signup.SignUp;
import com.example.recipeapp2.ViewModel.UserViewModel;
import com.example.recipeapp2.ViewModel.outils.Validform;

public class MainActivity extends AppCompatActivity {
    private TextView Register;
    private EditText email, password;
    private UserViewModel userViewModel;
    private ImageView submmit;

    @SuppressLint("MissingInflatedId")//probleme
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //liaison avec view
        this.Register = findViewById(R.id.Register);
        this.email = findViewById(R.id.Login_email);
        this.password = findViewById(R.id.Login_password);
        this.submmit = findViewById(R.id.submmit_login);
        //initialisation du viewmodel communication avec le Repository
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        //verification et redirection vers home
        submmit.setOnClickListener(v-> {
            String email = this.email.getText().toString().trim();
            String password = this.password.getText().toString().trim();

            String emailError = Validform.validateEmail(email);
            String passwordError = Validform.validatePassword(password);


            boolean isValid = true;

            if (emailError != null) {
                this.email.setError(emailError);
                isValid = false;
            }

            if (passwordError != null) {
                this.password.setError(passwordError);
                isValid = false;
            }

            //verification de l existance f lbase de donnée
            userViewModel.getUserByEmail(email).observe(this, user -> {
                if (user != null && user.getPassword().equals(password)) {
                    Toast.makeText(this, "Connexion réussie !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, Home.class));
                    finish();

                } else
                    Toast.makeText(this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();

            });
        });
        //redirectioin ves signup
        Register.setOnClickListener(v -> {
                    Intent intent = new Intent(getApplicationContext(), SignUp.class);
                    startActivity(intent);
                    finish();
                });
    }
}