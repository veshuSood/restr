package com.example.restr

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CartActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val cartLayout = findViewById<LinearLayout>(R.id.llCartItems)
        val tvTotal = findViewById<TextView>(R.id.tvTotalAmount)
        val tvTaxes = findViewById<TextView>(R.id.tvTaxes)
        val tvGrand = findViewById<TextView>(R.id.tvGrandTotal)
        val btnOrder = findViewById<Button>(R.id.btnPlaceOrder)

        val cart = CartManager.getItems()

        if (cart.isEmpty()) {
            val emptyText = TextView(this)
            emptyText.text = "Your cart is empty."
            emptyText.textSize = 18f
            emptyText.setPadding(16, 16, 16, 16)
            cartLayout.addView(emptyText)
            tvTotal.text = ""
            tvTaxes.text = ""
            tvGrand.text = ""
        } else {
            for ((dishName, item) in cart) {
                val itemText = TextView(this)
                itemText.text = "$dishName × ${item.quantity} = ₹${item.quantity * item.price}"
                itemText.textSize = 16f
                itemText.setPadding(0, 16, 0, 0)
                cartLayout.addView(itemText)
            }

            val netTotal = CartManager.getNetTotal()
            val tax = netTotal * 0.025
            val grandTotal = netTotal + tax * 2

            tvTotal.text = "Total: ₹$netTotal"
            tvTaxes.text = "CGST (2.5%): ₹${tax.toInt()}\nSGST (2.5%): ₹${tax.toInt()}"
            tvGrand.text = "Grand Total: ₹${grandTotal.toInt()}"

            btnOrder.setOnClickListener {
                Toast.makeText(this, "Order Placed!", Toast.LENGTH_SHORT).show()
                CartManager.clearCart()
                finish() // go back to home
            }
        }
    }
}

