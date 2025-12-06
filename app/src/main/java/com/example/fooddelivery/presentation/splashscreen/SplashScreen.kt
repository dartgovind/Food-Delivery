package com.example.fooddelivery.presentation.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.fooddelivery.R
import com.example.fooddelivery.navigation.Routes
import com.example.fooddelivery.ui.theme.SFRounded


@Composable
fun SplashScreen(
    navHostController: NavHostController
) {
    val mainBackgroundColor = Color(0xFFFF4B3A) // Orange background
    val gradientColor = Color(0xFFFF470B) // Gradient overlay color
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(mainBackgroundColor)
    ) {
        val screenWidth = maxWidth

        Column(modifier = Modifier.fillMaxSize()) {

            // Top Logo
            Box(
                modifier = Modifier
                    .padding(top = 30.dp, start = 25.dp)
                    .size(73.dp)
                    .background(Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chefcap),
                    contentDescription = "Logo",
                    modifier = Modifier.size(46.dp)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Title
            Text(
                text = "Food for\nEveryone",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
                fontFamily = SFRounded,
                lineHeight = 50.sp,
                modifier = Modifier.padding(start = 25.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Overlapping Images
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.toyface),
                    contentDescription = "Man",
                    modifier = Modifier
                        .width(screenWidth * 0.6f)
                        .fillMaxHeight(0.85f)
                        .offset(x = screenWidth * 0.4f, y = 30.dp)
                        .zIndex(0f)
                )
                Image(
                    painter = painterResource(id = R.drawable.toygirlface),
                    contentDescription = "Girl",
                    modifier = Modifier
                        .width(screenWidth * 0.7f)
                        .fillMaxHeight()
                        .offset(x = (-10).dp, y = (-40).dp)
                        .zIndex(1f)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(2f)
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    0.0f to Color.Transparent,
                                    0.4f to Color.Transparent,
                                    0.7f to gradientColor.copy(alpha = 0.6f),
                                    0.85f to gradientColor.copy(alpha = 0.9f),
                                    1.0f to gradientColor
                                )
                            )
                        )
                )
            }

            // Bottom Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 42.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        navHostController.navigate(Routes.LoginSignUpScreen)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(70.dp)
                ) {
                    Text(
                        text = "Get started",
                        color = mainBackgroundColor,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                        fontFamily = SFRounded
                    )
                }
            }
        }
    }
}


