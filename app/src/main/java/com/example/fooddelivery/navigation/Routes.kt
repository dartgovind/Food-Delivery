package com.example.fooddelivery.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes{

    @Serializable
    data object SplashScreen: Routes()

    @Serializable
    data object LoginSignUpScreen: Routes()

}