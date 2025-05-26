package com.example.restr

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CuisineActivity : AppCompatActivity() {

    private lateinit var rvDishes: RecyclerView
    private lateinit var dishList: ArrayList<Dish>
    private lateinit var cuisineName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuisine)

        // Get passed cuisine name
        cuisineName = intent.getStringExtra("cuisineName") ?: "Unknown Cuisine"
        findViewById<TextView>(R.id.tvCuisineTitle).text = cuisineName

        // Set up RecyclerView
        rvDishes = findViewById(R.id.rvDishes)
        rvDishes.layoutManager = LinearLayoutManager(this)

        // Populate dish list based on cuisine (static data here)
        dishList = ArrayList()
        when (cuisineName) {
            "North Indian" -> {
                dishList.add(Dish("Butter Chicken", 200, 4.5f,R.drawable.butter_chicken))
                dishList.add(Dish("Paneer Tikka", 180, 4.1f,R.drawable.paneer_tikka))
            }
            "Chinese" -> {
                dishList.add(Dish("Hakka Noodles", 150,3.5f, R.drawable.hakka_noodles))
                dishList.add(Dish("Manchurian", 160, 4.7f,R.drawable.manchurian))
            }
            "Mexican" -> {
                dishList.add(Dish("Tacos", 190, 3f,R.drawable.tacos))
                dishList.add(Dish("Churros", 60,4f, R.drawable.churros))
            }
            "South Indian" -> {
                dishList.add(Dish("Dosa", 140,3.6f, R.drawable.dosa))
                dishList.add(Dish("Idli", 80,3.9f, R.drawable.idli))
            }
            "Italian" -> {
                dishList.add(Dish("Pizza", 290,4.7f, R.drawable.pizza))
                dishList.add(Dish("Pasta", 260, 4.3f,R.drawable.pasta))
            }

        }

        val adapter = DishAdapter(dishList)
        rvDishes.adapter = adapter
    }
}
