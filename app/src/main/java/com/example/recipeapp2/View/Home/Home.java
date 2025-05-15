package com.example.recipeapp2.View.Home;

import android.os.Bundle;
import android.widget.Toast;

//import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.recipeapp2.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        Toast.makeText(this, "HomeActivity lancée", Toast.LENGTH_SHORT).show();


    }
}