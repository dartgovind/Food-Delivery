package com.example.fooddelivery.presentation.seemorescreen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddelivery.R
import com.example.fooddelivery.navigation.Routes
import com.example.fooddelivery.ui.theme.SFRounded


data class FoodItem(
    val id: Int,
    val name: String,
    val price: String,
    val imageRes: Int
)


// --- 2. Sample Data
val sampleFoodItems = listOf(
    FoodItem(1, "Veggie tomato mix", "1900", R.drawable.food1),
    FoodItem(2, "Egg and cucumber", "1900", R.drawable.food2),
    FoodItem(3, "Fried chicken m.", "1900", R.drawable.food3),
    FoodItem(4, "Moi-moi and ekpa", "1900", R.drawable.food4),
    FoodItem(5, "Moi-moi special", "1900", R.drawable.food1),
    FoodItem(6, "Egg salad", "1900", R.drawable.food2)
)

// --- 3. Colors
val BackgroundColor = Color(0xFFE0E0E0)
val ContentBackgroundColor = Color(0xFFF0F0F0)
val TextColor = Color.Black


@Composable
fun SeeMoreScreen(navHostController: NavHostController) {

    var searchText by remember { mutableStateOf("Spicy chickens") }
    var isEditing by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            IconButton(onClick = {
                navHostController.popBackStack()
                navHostController.navigate(Routes.HomeScreen)
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = TextColor
                )
            }

            Spacer(modifier = Modifier.width(16.dp))


            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable { isEditing = true }
            ) {
                BasicTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    singleLine = true,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = SFRounded
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(ContentBackgroundColor)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Found ${sampleFoodItems.size} results",
                    fontSize = 28.sp,
                    fontFamily = SFRounded,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextColor,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 20.dp)
                ) {
                    itemsIndexed(sampleFoodItems) { index, item ->
                        val topOffset = if (index % 2 == 1) 40.dp else 0.dp
                        Box(modifier = Modifier.padding(top = topOffset)) {
                            FoodItemCard(item)
                        }
                    }
                }
            }
        }
    }
}

// --- 5. FoodItemCard
@Composable
fun FoodItemCard(item: FoodItem) {
    val imageSize = 160.dp
    val overlap = imageSize / 3

    Box(
        modifier = Modifier
            .width(160.dp)
            .wrapContentHeight(),
        contentAlignment = Alignment.TopCenter
    ) {

        Card(
            modifier = Modifier
                .padding(top = overlap)
                .width(156.dp)
                .clickable { }
                .height(240.dp),
            shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = item.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 2,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "RS ${item.price}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFA4A0C)
                )
            }
        }

        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier.size(imageSize),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSeeMoreScreen() {
    SeeMoreScreen(rememberNavController())
}

