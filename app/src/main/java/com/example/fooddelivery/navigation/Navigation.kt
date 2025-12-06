package com.example.fooddelivery.navigation


import LoginSignUpScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fooddelivery.presentation.splashscreen.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.SplashScreen) {

        composable<Routes.SplashScreen> {
            SplashScreen(navHostController = navController)
        }

        composable<Routes.LoginSignUpScreen> {
            LoginSignUpScreen(navHostController = navController)
        }
    }


}