package com.example.fooddelivery.presentation.detailscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddelivery.R
import com.example.fooddelivery.ui.theme.SFRounded
import com.google.accompanist.pager.*
import androidx.compose.foundation.shape.RoundedCornerShape

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FoodDetailScreen() {

    val images = listOf(
        R.drawable.detailscreenfood,
        R.drawable.food4,
        R.drawable.food3
    )
    val pagerState = rememberPagerState()

    Scaffold(
        containerColor = Color(0xFFF5F5F5) // Fixed light gray background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { /* Navigate back */ },
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.White, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black // Fixed color
                    )
                }

                IconButton(
                    onClick = { /* Add to favorites */ },
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.White, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color.Black // Fixed color
                    )
                }
            }

            // Centered swipeable Food Images
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                HorizontalPager(
                    count = images.size,
                    state = pagerState,
                    modifier = Modifier.size(300.dp)
                ) { page ->
                    Image(
                        painter = painterResource(id = images[page]),
                        contentDescription = "Food Image",
                        modifier = Modifier
                            .size(300.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = Color(0xFFFF5722),
                    inactiveColor = Color.LightGray,
                    indicatorWidth = 8.dp,
                    spacing = 6.dp,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 0.dp)
                )
            }

            // Content Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Veggie tomato mix",
                    fontFamily = SFRounded,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black // Fixed
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "N1,900",
                    fontFamily = SFRounded,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF5722) // Fixed
                )

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Delivery info",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = SFRounded,
                        color = Color.Black // Fixed
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Delivered between Monday Aug and Thursday 20 from 8pm to 9:32 pm",
                        fontSize = 14.sp,
                        color = Color.Gray, // Fixed
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Return policy",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black // Fixed
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "All our foods are double checked before leaving our stores. In case you find a broken food, please contact our hotline immediately.",
                        fontSize = 14.sp,
                        fontFamily = SFRounded,
                        color = Color.Gray, // Fixed
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = { /* Add to cart */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF5722) // Fixed
                        ),
                        shape = RoundedCornerShape(28.dp)
                    ) {
                        Text(
                            text = "Add to cart",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White // Fixed
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFoodDetailScreen() {
    FoodDetailScreen()
}
