package com.example.recipeapp2.View.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.recipeapp2.Model.model.Plat;
import com.example.recipeapp2.R;
import com.example.recipeapp2.View.Plat.PlatDetailActivit;

import java.util.List;

public class SuggestionAdapter extends ArrayAdapter<Plat> {

    private final Context context;
    private final List<Plat> plats;

    public SuggestionAdapter(Context context, List<Plat> plats) {
        super(context, 0, plats);
        this.context = context;
        this.plats = plats;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_suggestion, parent, false);
        }
        Plat plat = plats.get(position);

        TextView title = view.findViewById(R.id.plat_title);
        ImageView image = view.findViewById(R.id.plat_image);

        title.setText(plat.getTitle());

        Glide.with(context)
                .load(plat.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .into(image);

        view.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlatDetailActivit.class);
            intent.putExtra("plat", plat);  // Envoi de l'objet Plat
            context.startActivity(intent);
        });
        return view;
    }

    @Override
    public int getCount() {
        return plats.size();
    }
}
