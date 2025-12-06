package com.example.fooddelivery.navigation


import LoginSignUpScreen
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fooddelivery.presentation.cartwishlistscreen.CartScreen
import com.example.fooddelivery.presentation.cartwishlistscreen.WishlistScreen
import com.example.fooddelivery.presentation.deliveryscreen.DeliveryScreen
import com.example.fooddelivery.presentation.detailscreen.FoodDetailScreen
import com.example.fooddelivery.presentation.home.HomeScreen
import com.example.fooddelivery.presentation.menu.MenuContent
import com.example.fooddelivery.presentation.menu.SlideMenuLayout
import com.example.fooddelivery.presentation.order.EmptyOrdersScreen
import com.example.fooddelivery.presentation.paymentscreen.PaymentScreen
import com.example.fooddelivery.presentation.profile.ProfileScreen
import com.example.fooddelivery.presentation.seemorescreen.SeeMoreScreen
import com.example.fooddelivery.presentation.splashscreen.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Routes.SplashScreen) {

        composable<Routes.SplashScreen> {
            SplashScreen(navHostController = navController)
        }

        composable<Routes.LoginSignUpScreen> {
            LoginSignUpScreen(navHostController = navController)
        }

        composable<Routes.HomeScreen> {
            SlideMenuLayout(
                menuWidth = 320.dp,
                peekWidth = 24.dp,
                menuContent = { onClose ->
                    MenuContent(onClose = onClose, onMenuItemClick = {item->
                        when (item) {
                            "Profile" -> navController.navigate(Routes.ProfileScreen)
                            "orders" -> navController.navigate(Routes.OrderScreen)
                            "Sign-out"->{
                                navController.navigate(Routes.LoginSignUpScreen){
                                    popUpTo(0){
                                        inclusive = true
                                    }
                                }
                            }
                            else -> {
                                Toast.makeText(context, "Coming soon..", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                },
                mainContent = { onMenuClick ->
                    HomeScreen(onMenuClick = onMenuClick, navHostController = navController)
                }
            )
        }

        composable<Routes.CartScreen> {
            CartScreen(navHostController = navController)
        }

        composable<Routes.WishlistScreen> {
            WishlistScreen(navHostController = navController)
        }

        composable<Routes.ProfileScreen> {
            ProfileScreen(navHostController = navController)
        }

        composable<Routes.OrderScreen> {
            EmptyOrdersScreen(navHostController = navController)
        }

        composable<Routes.SeeMoreScreen> {
            SeeMoreScreen(navHostController = navController)
        }

        composable<Routes.FoodDetailScreen> {
            FoodDetailScreen(navHostController = navController)
        }

        composable<Routes.PaymentScreen> {
            PaymentScreen(navHostController = navController)
        }

        composable<Routes.DeliveryScreen> {
            DeliveryScreen(navHostController = navController)
        }



    }


}