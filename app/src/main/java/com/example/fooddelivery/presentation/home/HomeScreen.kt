package com.example.fooddelivery.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddelivery.R
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavHostController
import com.example.fooddelivery.navigation.Routes
import com.example.fooddelivery.ui.theme.projectOrange

//@Preview(showSystemUi = true)
@Composable
fun HomeScreen(onMenuClick : () -> Unit, navHostController: NavHostController) {

    var selectedTab by remember { mutableStateOf("Foods") }
    val tabs = listOf("Foods", "Drinks", "Snacks", "Sauces")

    var search by remember { mutableStateOf("") }

    val foods = listOf(
        FoodItem(
            1,
            "Veggie tomato mix",
            "N1,900",
            R.drawable.ic_dummy_image
        ),
        FoodItem(
            2,
            "Spicy fish with sauce",
            "N2,300",
            R.drawable.ic_dummy_image
        ),
        FoodItem(
            1,
            "Veggie tomato mix",
            "N1,900",
            R.drawable.ic_dummy_image
        ),
        FoodItem(
            2,
            "Spicy fish with sauce",
            "N2,300",
            R.drawable.ic_dummy_image
        ),
        FoodItem(
            1,
            "Veggie tomato mix",
            "N1,900",
            R.drawable.ic_dummy_image
        ),
        FoodItem(
            2,
            "Spicy fish with sauce",
            "N2,300",
            R.drawable.ic_dummy_image
        )
    )

    Scaffold(
        bottomBar = { BottomNavigationBar{index ->
            //navigate to each screen as per index
            when (index) {
                0 -> navHostController.navigate(Routes.HomeScreen)
                1 -> navHostController.navigate(Routes.WishlistScreen)
                2 -> navHostController.navigate(Routes.ProfileScreen)
                3 -> {}
            }
        } }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(paddingValues)
        ) {
            // Header Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding()
            ) {
                Spacer(modifier = Modifier.height(12.dp))

                // Top Bar
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .background(Color(0xFFF5F5F5)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onMenuClick) {
                        Icon(
                            painter = painterResource(R.drawable.ic_menu),
                            contentDescription = "Menu",
                            modifier = Modifier.size(28.dp)
                                .padding(start = 4.dp)
                        )
                    }
                    IconButton(onClick = {
                        navHostController.navigate(Routes.CartScreen)
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_cart),
                            contentDescription = "Cart",

                            modifier = Modifier.size(28.dp)
                        )
                    }
                }


                // Everything from Title onwards is inside this LazyColumn (scrollable)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF5F5F5)),
                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // --- Title (first item in lazy column) ---
                    item {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Delicious",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Text(
                                text = "food for you",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }

                    item { Spacer(modifier = Modifier.height(12.dp)) }

                    // --- Search Bar ---
                    item {
                        OutlinedTextField(
                            value =search,
                            onValueChange = {search=it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(30.dp))
                                .padding(horizontal = 12.dp),
                            placeholder = { Text("Search", color = Color.Gray) },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Search,
                                    contentDescription = "Search",
                                    tint = Color.Gray
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                focusedContainerColor = Color(0x52D5D5D5),
                                unfocusedContainerColor = Color(0x97D5D5D5)
                            ),
                            shape = RoundedCornerShape(30.dp),
                            singleLine = true
                        )
                    }

                    // spacer
                    item { Spacer(modifier = Modifier.height(4.dp)) }

                    // --- Tabs Row ---
                    item {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = 12.dp),
                            horizontalArrangement = Arrangement.spacedBy(32.dp),
                            contentPadding = PaddingValues(horizontal = 4.dp)
                        ) {
                            items(tabs) { tab ->
                                Column(
                                    modifier = Modifier
                                        .clickable { selectedTab = tab }
                                        .padding(vertical = 4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = tab,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = if (selectedTab == tab) projectOrange else Color.Gray
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    if (selectedTab == tab) {
                                        Box(
                                            modifier = Modifier
                                                .width(40.dp)
                                                .height(3.dp)
                                                .background(
                                                   projectOrange,
                                                    RoundedCornerShape(2.dp)
                                                )
                                        )
                                    } else {
                                        Spacer(modifier = Modifier.height(3.dp))
                                    }
                                }
                            }
                        }
                    }



                    // --- See More row ---
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(end = 12.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                text = "see more",
                                color = projectOrange,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier
                                    .clickable {
                                        navHostController.navigate(Routes.SeeMoreScreen)
                                    }
                            )
                        }
                    }

                    // spacer
                    item { Spacer(modifier = Modifier.height(8.dp)) }



                    item {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(600.dp),
                            contentPadding = PaddingValues(8.dp),
                            verticalArrangement = Arrangement.spacedBy(35.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(foods) { food ->
                                FoodCard(food,
                                    onItemClick = {
                                        navHostController.navigate(Routes.FoodDetailScreen)
                                    })
                            }
                        }
                    }
                }
            }
        }
    }
}