package com.example.fooddelivery.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes{

    @Serializable
    data object SplashScreen: Routes()

    @Serializable
    data object LoginSignUpScreen: Routes()

    @Serializable
    data object HomeScreen: Routes()

    @Serializable
    data object CartScreen: Routes()

    @Serializable
    data object WishlistScreen: Routes()

    @Serializable
    data object ProfileScreen: Routes()

    @Serializable
    data object OrderScreen: Routes()

    @Serializable
    data object SeeMoreScreen: Routes()

    @Serializable
    data object FoodDetailScreen: Routes()

    @Serializable
    data object PaymentScreen: Routes()

    @Serializable
    data object DeliveryScreen: Routes()


}