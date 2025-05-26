package com.example.restr

data class CartItem(val quantity: Int, val price: Int)

object CartManager {
    val cartItems = mutableMapOf<String, CartItem>()  // dishName → quantity + price

    fun addDish(name: String, price: Int) {
        val item = cartItems[name]
        if (item != null) {
            cartItems[name] = item.copy(quantity = item.quantity + 1)
        } else {
            cartItems[name] = CartItem(quantity = 1, price = price)
        }
    }

    fun getItems(): Map<String, CartItem> = cartItems

    fun clearCart() {
        cartItems.clear()
    }

    fun getNetTotal(): Int {
        return cartItems.values.sumOf { it.quantity * it.price }
    }
}

