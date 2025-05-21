package com.example.recipeapp2.View.Country;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.recipeapp2.Model.model.Plat;
import com.example.recipeapp2.R;
import com.example.recipeapp2.View.Plat.PlatDetailActivit;

import java.util.List;


public class CountryPlatAdapter extends RecyclerView.Adapter<CountryPlatAdapter.PlatViewHolder> {

    private final List<Plat> platList;
    private final Context context;

    public CountryPlatAdapter(List<Plat> platList, Context context) {
        this.platList = platList;
        this.context = context;
    }
    @NonNull
    @Override
    public PlatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_country_plat, parent, false);
        return new PlatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatViewHolder holder, int position) {
        Plat plat = platList.get(position);
        holder.title.setText(plat.getTitle());

        // les plat clickable
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlatDetailActivit.class);
            intent.putExtra("idMeal", plat.getIdMeal());
            context.startActivity(intent);
        });



        // gilde bach nchargiw l image mn l internet
        Glide.with(context)
                .load(plat.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return platList.size();
    }


    //constructeur dyal view holder bach manb9awch ndiro byid retuliser v wajda
    public static class PlatViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
//        RatingBar ratingBar;

        public PlatViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.plat_image);
            title = itemView.findViewById(R.id.plat_title);
//            ratingBar = itemView.findViewById(R.id.plat_rating);
        }
    }
}