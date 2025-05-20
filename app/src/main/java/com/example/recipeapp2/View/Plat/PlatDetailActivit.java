package com.example.recipeapp2.View.Plat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.recipeapp2.Data.model.Plat;
import com.example.recipeapp2.R;

public class PlatDetailActivit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_plat_detail);
        Plat plat = (Plat) getIntent().getSerializableExtra("plat");
        TextView title = findViewById(R.id.platTitle);
        ImageView image = findViewById(R.id.platImage);
        TextView ingredients = findViewById(R.id.platIngredients);
        TextView description = findViewById(R.id.platInstructions);

        if (plat != null) {
            title.setText(plat.getTitle());

            Glide.with(this)
                    .load(plat.getImageUrl())
                    .placeholder(R.drawable.placeholder)
                    .into(image);


            StringBuilder ingredientList = new StringBuilder();
            if (plat.getIngredient1() != null) ingredientList.append("• ").append(plat.getIngredient1()).append("\n");
            if (plat.getIngredient2() != null) ingredientList.append("• ").append(plat.getIngredient2()).append("\n");
            if (plat.getIngredient3() != null) ingredientList.append("• ").append(plat.getIngredient3()).append("\n");
            if (plat.getIngredient4() != null) ingredientList.append("• ").append(plat.getIngredient4());


            ingredients.setText(ingredientList.toString());
            description.setText(plat.getInstructions());
        }

    }
}