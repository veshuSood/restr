package com.example.restr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CuisineAdapter extends RecyclerView.Adapter<CuisineAdapter.CuisineViewHolder> {

    Context context;
    List<Cuisine> cuisineList;
    OnCuisineClickListener listener;

    // Custom listener interface to handle clicks
    public interface OnCuisineClickListener {
        void onCuisineClick(Cuisine cuisine);
    }

    public CuisineAdapter(Context context, List<Cuisine> cuisineList, OnCuisineClickListener listener) {
        this.context = context;
        this.cuisineList = cuisineList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CuisineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cuisine, parent, false);
        return new CuisineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CuisineViewHolder holder, int position) {
        Cuisine cuisine = cuisineList.get(position);
        holder.textCuisineName.setText(cuisine.getName());
        holder.imageCuisine.setImageResource(cuisine.getImageResId());

        holder.itemView.setOnClickListener(v -> listener.onCuisineClick(cuisine));
    }

    @Override
    public int getItemCount() {
        return cuisineList.size();
    }

    public static class CuisineViewHolder extends RecyclerView.ViewHolder {
        ImageView imageCuisine;
        TextView textCuisineName;

        public CuisineViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCuisine = itemView.findViewById(R.id.imageCuisine);
            textCuisineName = itemView.findViewById(R.id.textCuisineName);
        }
    }
}
