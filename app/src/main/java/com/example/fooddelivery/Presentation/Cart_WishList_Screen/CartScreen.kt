package com.example.fooddelivery.Presentation.Cart_WishList_Screen

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddelivery.R
import com.example.fooddelivery.ui.theme.projectOrange
import com.example.fooddelivery.ui.theme.projectWhite
import kotlin.math.roundToInt

data class CartItem(
    val id: Int,
    val name: String,
    val price: String,
    val imgResId: Int,
    var quantity: Int = 1
)

@Composable
fun CartScreen() {
    var cartItems by remember {
        mutableStateOf(
            listOf(
                CartItem(
                    1,
                    "Veggie tomato mix",
                    "#1,900",
                    R.drawable.ic_dummy_image
                ),
                CartItem(
                    2,
                    "Fishwith mix orange....",
                    "#1,900",
                    R.drawable.ic_dummy_image                ),
                CartItem(
                    3,
                    "Veggie tomato mix",
                    "#1,900",
                    R.drawable.ic_dummy_image                )
            )
        )
    }

    Scaffold(
        topBar = {
            CartTopBar()
        },
        bottomBar = {
            OrderButton()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(projectWhite)
                .padding(paddingValues)
        ) {
            // jab swip hoga

            // Cart items list
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = cartItems,
                    key = { it.id }
                ) { item ->
                    SwipeableCartItem(
                        item = item,
                        onDelete = {
                            cartItems = cartItems.filter { it.id != item.id }
                        },
                        onQuantityChange = { newQuantity ->
                            cartItems = cartItems.map {
                                if (it.id == item.id) it.copy(quantity = newQuantity)
                                else it
                            }
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Cart",
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

@Composable
fun SwipeableCartItem(
    item: CartItem,
    onDelete: () -> Unit,
    onQuantityChange: (Int) -> Unit
) {
    // raw drag value
    var dragOffset by remember { mutableStateOf(0f) }

    // smooth animation for swipe
    val animatedOffset by animateFloatAsState(
        targetValue = dragOffset,
        animationSpec = tween(
            durationMillis = 250,
            easing = FastOutSlowInEasing
        ),
        label = "cartSwipeOffset"
    )
    val density = LocalDensity.current
    val maxSwipe =  with(density) { -160.dp.toPx() }
    val swipeThreshold = maxSwipe / 2f

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {
        //background favorite and  Delete
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 24.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            //  favourite icon
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(projectOrange)
                    .clickable {  },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(projectOrange)
                    .clickable { onDelete() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset { IntOffset(animatedOffset.roundToInt(), 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            val newOffset = dragOffset + dragAmount

                            dragOffset = newOffset.coerceIn(maxSwipe, 0f)
                        },
                        onDragEnd = {
                            dragOffset = if (dragOffset < swipeThreshold) {
                                maxSwipe
                            } else {
                                0f
                            }
                        }
                    )
                },
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
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
                        .size(100.dp)
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


                Spacer(modifier = Modifier.width(16.dp))

                // Item details
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = item.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {

                        Text(
                            text = item.price,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = projectOrange
                        )

                        Spacer(modifier = Modifier.width(36.dp))

                        //quantity
                        QuantityControl(
                            quantity = item.quantity,
                            onQuantityChange = onQuantityChange
                        )


                    }
                    
                }


            }
        }
    }
}


@Composable
fun QuantityControl(
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .background(projectOrange, RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        IconButton(
            onClick = { if (quantity > 1) onQuantityChange(quantity - 1) },
            modifier = Modifier.size(20.dp)
                .offset(y = (-6.dp))
        ) {
            Text(
                text = "-",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = quantity.toString(),
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        IconButton(
            onClick = { onQuantityChange(quantity + 1) },
            modifier = Modifier.size(20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Increase",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun OrderButton() {
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
@Preview(showSystemUi = true)
@Composable
fun CartScreenPreview() {
    MaterialTheme {
        CartScreen()
    }
}