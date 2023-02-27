package com.examplecafe.littlelemon.auxiliary

/*Singleton that will be filled as the user adds an order to the cart.*/
object orderSingleton {
    private val orderMap = mutableMapOf<Int, DishData>()

    fun addToOrder(item: DishData) {
        orderMap[item.id] = DishData(
            item.id,
            item.title,
            item.description,
            item.price,
            item.image,
            item.category,
            item.count
        )
    }

    fun removeFromOrder(itemId: Int) {
        orderMap.remove(itemId)
    }

    fun getCartMap(): Map<Int, DishData> {
        return orderMap
    }
}