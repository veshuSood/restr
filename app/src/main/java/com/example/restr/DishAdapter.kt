package com.example.restr

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class DishAdapter(private val dishList: List<Dish>) :
    RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dishName: TextView = itemView.findViewById(R.id.tvDishName)
        val price: TextView = itemView.findViewById(R.id.tvPrice)
        val image: ImageView = itemView.findViewById(R.id.imgDish)
        val btnAdd: Button = itemView.findViewById(R.id.btnAddDish)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dish, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = dishList[position]
        holder.dishName.text = dish.name
        holder.price.text = "₹${dish.price}"
        holder.image.setImageResource(dish.imageResId)
        holder.btnAdd.setOnClickListener {
            CartManager.addDish(dish.name, dish.price)
            Toast.makeText(holder.itemView.context, "${dish.name} added to cart!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return dishList.size
    }
}
