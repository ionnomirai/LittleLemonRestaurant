package com.examplecafe.littlelemon

interface Destinations{
    val route:String
    val title:String
}

object RegistrationDes: Destinations{
    override val route: String = "RegistrationScreen"
    override val title: String = "Registration"
}

object HomeDes: Destinations{
    override val route: String = "HomeScreen"
    override val title: String = "Home"
}

object SingleMenuDishDes: Destinations{
    override val route: String = "SingleDishScreen"
    override val title: String = "SingleDish"
}

object ProfileDes: Destinations{
    override val route: String = "ProfileScreen"
    override val title: String = "Profile"
}

object CartDes: Destinations{
    override val route: String = "CartScreen"
    override val title: String = "Cart"
}