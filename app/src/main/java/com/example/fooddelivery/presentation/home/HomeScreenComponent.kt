package com.example.fooddelivery.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddelivery.R
import com.example.fooddelivery.ui.theme.projectOrange

//@Preview(showSystemUi = true)
@Composable
fun BottomNavigationBar(
    onItemClick: (Int) -> Unit
) {

    var selectedIndex by remember { mutableStateOf(0) }

    val items = listOf(
        Pair(R.drawable.ic_home, R.drawable.ic_home_fill),
        Pair(R.drawable.ic_favorite, R.drawable.ic_favorite_fill),
        Pair(R.drawable.ic_profile, R.drawable.ic_person_fill),
        Pair(R.drawable.ic_history, R.drawable.ic_history_fill)
    )

    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier.height(80.dp)
    ) {

        items.forEachIndexed { index, item ->

            val isSelected = index == selectedIndex

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (isSelected) item.second else item.first
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                },
                selected = isSelected,
                onClick = {
                    selectedIndex = index
                    onItemClick(index)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = projectOrange, // orange
                    unselectedIconColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

data class FoodItem(
    val id: Int,
    val name: String,
    val price: String,
    val ingRseId: Int
)

@Composable
fun FoodCard(
    food: FoodItem,
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .clickable {
                onItemClick()
            },
        contentAlignment = Alignment.TopCenter
    ) {
        // Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = food.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    lineHeight = 26.sp,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = food.price,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = projectOrange,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Floating Image
        Image(
            painter = painterResource(id = food.ingRseId),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .offset(y = (-18).dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

