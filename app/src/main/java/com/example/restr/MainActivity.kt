package com.example.restr

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.content.res.Configuration
import android.widget.Button
import android.widget.ToggleButton
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private lateinit var rvCuisines: RecyclerView
    private lateinit var cuisineList: ArrayList<Cuisine>
    private lateinit var toggleLanguage: ToggleButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        val lang = getSharedPreferences("prefs", MODE_PRIVATE).getString("app_lang", "en") ?: "en"
        setLocale(lang)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toggleLanguage = findViewById(R.id.toggleLanguage)
        toggleLanguage.isChecked = lang == "hi"

        toggleLanguage.setOnCheckedChangeListener { _, isChecked ->
            val selectedLang = if (isChecked) "hi" else "en"
            saveLanguage(selectedLang)
            setLocale(selectedLang)
            recreate()
        }

        // Or activity_main.xml depending on your layout file

        // Step 1: Find RecyclerView
        rvCuisines = findViewById(R.id.rvCuisines)

        // Step 2: Add cuisines to list
        cuisineList = ArrayList()
        cuisineList.add(Cuisine("North Indian", R.drawable.north_indian))
        cuisineList.add(Cuisine("Chinese", R.drawable.chinese))
        cuisineList.add(Cuisine("Mexican", R.drawable.mexican))
        cuisineList.add(Cuisine("South Indian", R.drawable.south_indian))
        cuisineList.add(Cuisine("Italian", R.drawable.italian))

        // Step 3: Set up adapter
        val adapter = CuisineAdapter(this, cuisineList, object : CuisineAdapter.OnCuisineClickListener {
            override fun onCuisineClick(cuisine: Cuisine) {
                // Handle item click
                val intent = Intent(this@MainActivity, CuisineActivity::class.java)
                intent.putExtra("cuisineName", cuisine.name)
                startActivity(intent)
            }
        })

        // Step 4: Set LayoutManager and Adapter
        rvCuisines.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCuisines.adapter = adapter
        val rvTopDishes = findViewById<RecyclerView>(R.id.rvTopDishes)
        val topDishes = arrayListOf(
            Dish("Butter Chicken", 200, 4.5f, R.drawable.butter_chicken),
            Dish("Manchurian", 150, 4.2f, R.drawable.manchurian),
            Dish("Pizza", 250, 4.7f, R.drawable.pizza)
        )

        rvTopDishes.layoutManager = LinearLayoutManager(this)
        rvTopDishes.adapter = TopDishAdapter(topDishes)

        val btnCart = findViewById<Button>(R.id.btnCart)
        btnCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }



    }
    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
    }

    private fun saveLanguage(lang: String) {
        val prefs = getSharedPreferences("prefs", MODE_PRIVATE).edit()
        prefs.putString("app_lang", lang)
        prefs.apply()
    }
}
