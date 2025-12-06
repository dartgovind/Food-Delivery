package com.example.fooddelivery.presentation.menu


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class MenuItem(
    val icon: ImageVector,
    val title: String,
    val onClick: () -> Unit = {}
)

@Composable
fun MenuContent(
    onClose: () -> Unit,
    onMenuItemClick: (String) -> Unit
) {
    val menuItems = listOf(
        MenuItem(Icons.Default.Person, "Profile"),
        MenuItem(Icons.Default.ShoppingCart, "orders"),
        MenuItem(Icons.Default.Home, "offer and promo"),
        MenuItem(Icons.Default.CheckCircle, "Privacy policy"),
        MenuItem(Icons.Default.Person, "Security")
    )

    // Animation for menu items
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    // Use same background color as your mock
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Color(0xFFFF5722)),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // Menu Items with staggered animation
        menuItems.forEachIndexed { index, item ->
            AnimatedMenuItem(
                item = item,
                index = index,
                visible = visible,
                onClick = {
                    onMenuItemClick(item.title)
                    onClose()
                }
            )

            if (index < menuItems.size - 1) {
                Divider(
                    color = Color.White.copy(alpha = 0.3f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Sign-out button with animation
        AnimatedSignOutButton(
            visible = visible,
            onSignOutClicked = {
                onMenuItemClick("Sign-out")
                onClose()
            })

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun AnimatedMenuItem(
    item: MenuItem,
    index: Int,
    visible: Boolean,
    onClick: () -> Unit
) {
    val offsetX by animateDpAsState(
        targetValue = if (visible) 0.dp else (-50).dp,
        animationSpec = tween(
            durationMillis = 400,
            delayMillis = index * 80,
            easing = FastOutSlowInEasing
        ),
        label = "offsetX"
    )

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(
            durationMillis = 400,
            delayMillis = index * 80,
            easing = FastOutSlowInEasing
        ),
        label = "alpha"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = offsetX)
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            tint = Color.White,
            modifier = Modifier
                .size(28.dp)
                .graphicsLayer { scaleX = alpha; scaleY = alpha }
        )

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = item.title,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.graphicsLayer { scaleX = alpha; scaleY = alpha }
        )
    }
}

@Composable
fun AnimatedSignOutButton(
    visible: Boolean,
    onSignOutClicked: () -> Unit = {}
) {
    val offsetX by animateDpAsState(
        targetValue = if (visible) 0.dp else (-50).dp,
        animationSpec = tween(
            durationMillis = 400,
            delayMillis = 500,
            easing = FastOutSlowInEasing
        ),
        label = "signOutOffset"
    )

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(
            durationMillis = 400,
            delayMillis = 500,
            easing = FastOutSlowInEasing
        ),
        label = "signOutAlpha"
    )

    Row(
        modifier = Modifier
            .offset(x = offsetX)
            .clickable {
                onSignOutClicked()
            }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Sign-out",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.graphicsLayer { scaleX = alpha; scaleY = alpha }
        )

        Spacer(modifier = Modifier.width(16.dp))

        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Sign out",
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .graphicsLayer { scaleX = alpha; scaleY = alpha }
        )
    }
}

/**
 * SlideMenuLayout - integrates your MenuContent & MainContent
 */
@Composable
fun SlideMenuLayout(
    menuWidth: Dp = 320.dp,
    peekWidth: Dp = 24.dp,
    contentScrimColor: Color = Color.Black.copy(alpha = 0.4f),
    menuContent: @Composable (onClose: () -> Unit) -> Unit,
    mainContent: @Composable (onMenuClick: () -> Unit) -> Unit
) {
    var isMenuOpen by remember { mutableStateOf(false) }

    // animated properties
    val targetOffsetX = if (isMenuOpen) menuWidth - peekWidth else 0.dp
    val offsetX by animateDpAsState(
        targetValue = targetOffsetX,
        animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing)
    )

    val targetScale = if (isMenuOpen) 0.92f else 1f
    val scale by animateFloatAsState(targetValue = targetScale, animationSpec = tween(350))

    val targetCorner = if (isMenuOpen) 20.dp else 0.dp
    val cornerRadius by animateDpAsState(targetValue = targetCorner, animationSpec = tween(350))

    val density = LocalDensity.current
    val corner = if (isMenuOpen) 20.dp else 0.dp
    val shadowRadious by animateDpAsState(targetValue = corner, animationSpec = tween(350))

    Box(modifier = Modifier.fillMaxSize()) {
        // Menu background/content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFF5722))
        ) {
            menuContent {
                // close lambda passed down
                isMenuOpen = false
            }
        }

        // scrim when open
        if (isMenuOpen) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = menuWidth, top = 60.dp, bottom = 20.dp)
                    .background(contentScrimColor)
                    .clip(RoundedCornerShape(12.dp))
            )
        }

        // main content on top with transform
        Box(modifier = Modifier.fillMaxSize()) { }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    translationX = with(density) { offsetX.toPx() }
                    scaleX = scale
                    scaleY = scale
                }
                .shadow(
                    if (isMenuOpen) 24.dp else 0.dp,
                    RoundedCornerShape(shadowRadious),
                    clip = false
                )
                .clip(RoundedCornerShape(cornerRadius))
                .clickable(enabled = isMenuOpen) {
                    // clicking main closes menu
                    isMenuOpen = false
                }
        ) {
            // pass the toggle function to main content
            mainContent {
                isMenuOpen = !isMenuOpen
            }
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun AnimatedMenuDrawerPreview() {
//    MaterialTheme {
//        SlideMenuLayout(
//            menuWidth = 320.dp,
//            peekWidth = 24.dp,
//            menuContent = { onClose ->
//                // Use your MenuContent here
//                MenuContent(onClose = onClose)
//            },
//            mainContent = { onMenuClick ->
//                // Use your MainContent here
//                //MainContent(onMenuClick = onMenuClick)
//                HomeScreen (onMenuClick = onMenuClick)
//            }
//        )
//    }
//}
