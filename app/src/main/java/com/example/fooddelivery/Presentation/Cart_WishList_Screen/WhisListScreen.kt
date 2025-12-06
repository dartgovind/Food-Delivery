package com.example.fooddelivery.Presentation.Cart_WishList_Screen
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddelivery.R
import com.example.fooddelivery.ui.theme.projectOrange
import com.example.fooddelivery.ui.theme.projectWhite


data class WishlistItem(
    val id: Int,
    val name: String,
    val price: String,
    val imgResId: Int,
    var isFavorited: Boolean = true
)


@Composable
fun WishlistScreen() {
    var wishlistItems by remember {
        mutableStateOf(
            listOf(
                WishlistItem(
                    1,
                    "Veggie tomato mix",
                    "#1,900",
                    R.drawable.ic_demo_image3,
                    true
                ),
                WishlistItem(
                    2,
                    "Fishwith mix orange....",
                    "#1,900",
                    R.drawable.ic_dummy_image2,
                    true
                ),
                WishlistItem(
                    3,
                    "Veggie tomato mix",
                    "#1,900",
                    R.drawable.ic_dummy_image2,
                    true
                ),
                WishlistItem(
                    4,
                    "Fishwith mix orange....",
                    "#1,900",
                    R.drawable.ic_dummy_image2,
                    true
                ),
                WishlistItem(
                    5,
                    "Veggie tomato mix",
                    "#1,900",
                    R.drawable.ic_dummy_image2,
                    true
                ),
                WishlistItem(
                    6,
                    "Fishwith mix orange....",
                    "#1,900",
                    R.drawable.ic_dummy_image2,
                    true
                ),
                WishlistItem(
                    7,
                    "Veggie tomato mix",
                    "#1,900",
                    R.drawable.ic_dummy_image2,
                    true
                ),
                WishlistItem(
                    8,
                    "Fishwith mix orange....",
                    "#1,900",
                    R.drawable.ic_dummy_image2,
                    true
                )
            )
        )
    }

    Scaffold(
        topBar = {
            WishlistTopBar()
        },
        bottomBar = {
            CompleteOrderButton()
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(projectWhite)
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = wishlistItems,
                key = { it.id }
            ) { item ->
                AnimatedVisibility(
                    visible = item.isFavorited,
                    exit = shrinkVertically() + fadeOut()
                ) {
                    WishlistItemCard(
                        item = item,
                        onFavoriteClick = {
                            wishlistItems = wishlistItems.map {
                                if (it.id == item.id) it.copy(isFavorited = false)
                                else it
                            }
                        },
                        onAddClick = {
                            // Add to cart logic
                        }
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Wishlist",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* Navigate back */ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = projectWhite
        )
    )
}


//@Preview(showSystemUi = true)
@Composable
fun WishlistItemCard(
    item: WishlistItem,
    onFavoriteClick: () -> Unit,
    onAddClick: () -> Unit
) {
    var isFavorited by remember { mutableStateOf(item.isFavorited) }
    val scale by animateFloatAsState(
        targetValue = if (isFavorited) 1f else 0.8f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "heartScale"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Food image
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .background(projectWhite),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = item.imgResId),
                        contentDescription = item.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Item details
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = item.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text =item.price,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = projectOrange
                    )


                    Row (modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End){
                        // Add button
                        Button(
                            onClick = { onAddClick },
                            modifier = Modifier
                                .width(90.dp)
                                .height(35.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = projectOrange
                            ),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "Add",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                    }


                }
            }

            // Heart icon (favorite button)

                Icon(
                    imageVector = if (isFavorited) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = projectOrange,
                    modifier = Modifier
                        .size(45.dp)
                        .scale(scale)
                        .padding(top = 10.dp, end = 15.dp)
                        //.offset(y = (-10.dp))
                        .align(Alignment.TopEnd)
                        .clickable {
                            isFavorited = !isFavorited
                            if (!isFavorited) {
                                onFavoriteClick()
                            }
                        }
                )

        }
    }
}


@Composable
fun CompleteOrderButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(projectWhite)
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        Button(
            onClick = { /* Complete order */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            shape = RoundedCornerShape(32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = projectOrange
            )
        ) {
            Text(
                text = "Complete order",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}



// Alternative: Animated heart with pulse effect
@Composable
fun AnimatedHeartIcon(
    isFavorited: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var clicked by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (clicked) 1.3f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        finishedListener = { clicked = false },
        label = "heartPulse"
    )

    IconButton(
        onClick = {
            clicked = true
            onClick()
        },
        modifier = modifier.size(44.dp)
    ) {
        Icon(
            imageVector = if (isFavorited) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "Favorite",
            tint = projectOrange,
            modifier = Modifier
                .size(32.dp)
                .scale(scale)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun WishlistScreenPreview() {
    MaterialTheme {
        WishlistScreen()
    }
}
