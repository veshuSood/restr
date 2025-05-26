package com.example.restr

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TopDishAdapter(private val dishList: List<Dish>) :
    RecyclerView.Adapter<TopDishAdapter.TopDishViewHolder>() {

    class TopDishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgDish: ImageView = itemView.findViewById(R.id.imgDish)
        val name: TextView = itemView.findViewById(R.id.tvDishName)
        val price: TextView = itemView.findViewById(R.id.tvDishPrice)
        val rating: TextView = itemView.findViewById(R.id.tvDishRating)
        val btnAdd: Button = itemView.findViewById(R.id.btnAddDish)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopDishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_top_dish, parent, false)
        return TopDishViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopDishViewHolder, position: Int) {
        val dish = dishList[position]
        holder.imgDish.setImageResource(dish.imageResId)
        holder.name.text = dish.name
        holder.price.text = "₹${dish.price}"
        holder.rating.text = "⭐ ${dish.rating}"


        holder.btnAdd.setOnClickListener {
            CartManager.addDish(dish.name, dish.price)
            Toast.makeText(holder.itemView.context, "${dish.name} added to cart!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int = dishList.size


}
