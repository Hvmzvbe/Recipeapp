package com.example.recipeapp2.View.signup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

//import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import com.example.recipeapp2.Model.model.User;
import com.example.recipeapp2.R;
import com.example.recipeapp2.View.login.MainActivity;
import com.example.recipeapp2.ViewModel.UserViewModel;
import com.example.recipeapp2.ViewModel.outils.Validform;

public class SignUp extends AppCompatActivity {
 EditText username, password, email;
 ImageView submmit;
 UserViewModel userViewModel;
    @SuppressLint("MissingInflatedId")//probleme
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        //liaison des vues
        username = findViewById(R.id.signUp_Username);
        password = findViewById(R.id.signUp_password);
        email = findViewById(R.id.signUp_email);
        submmit = (ImageView) findViewById(R.id.submit_sign_up);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);//observer livedata afin de maj de UI
        //le click sur l img pour inserer les donnés dyal les champs
        submmit.setOnClickListener(v->{
            User user = new User();
            user.setUsername(username.getText().toString().trim());
            user.setEmail(email.getText().toString().trim());
            user.setPassword(password.getText().toString().trim());

            String usernameError = Validform.validateUsername(user.getUsername());
            String emailError = Validform.validateEmail(user.getEmail());
            String passwordError = Validform.validatePassword(user.getPassword());
            boolean valid = true;
            if (usernameError != null) {
                username.setError(usernameError);
                valid = false;
            } else if (emailError != null) {
                email.setError(emailError);
                valid = false;
            } else if (passwordError != null) {
                password.setError(passwordError);
                valid=false;
            }
            if (valid) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                //inserer les donnés dyal les champs f basedonnées
                userViewModel.insertUser(user);
                // Redirection vers la page de connexion
                startActivity(intent);
                finish();
            }

        });

    }
}