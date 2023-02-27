package com.examplecafe.littlelemon.auxiliary

/*The data class needed to store and use information about an object
    obtained from JSON or a database.*/
data class DishData(
    var id: Int,
    var title: String,
    var description: String,
    var price: Double,
    var image: String,
    var category: String,
    var count:Int
)