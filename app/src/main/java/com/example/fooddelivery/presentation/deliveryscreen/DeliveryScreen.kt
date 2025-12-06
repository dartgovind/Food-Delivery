package com.example.fooddelivery.presentation.deliveryscreen

import PopUpScreen
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fooddelivery.navigation.Routes
import com.example.fooddelivery.ui.theme.SFRounded

data class Address(
    val name: String,
    val addressLine: String,
    val phone: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryScreen(
    addresses: List<Address> = listOf(
        Address(
            name = "Marvis Kparoboo",
            addressLine = "Km 5 refinery road opposite re public road, effurun, delta state",
            phone = "+234 9011039271"
        )
    ),
    deliveryOptions: List<String> = listOf("Door delivery", "Pick up"),
    totalAmount: String = "23,000",
    navHostController: NavHostController
) {
    val orange = Color(0xFFFF6B35)
    val textOrange = Color(0xFFFA4A0C)
    var selectedDeliveryIndex by remember { mutableIntStateOf(0) }
    var showPopup by remember { mutableStateOf(false) }


    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Checkout",
                        fontFamily = SFRounded
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navHostController.popBackStack()
                        },
                        modifier = Modifier.padding(start = 10.dp)
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(horizontal = 26.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Delivery",
                fontFamily = SFRounded,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- ADDED: Address details header and Change button ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    fontFamily = SFRounded,
                    text = "Address details",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "change",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textOrange,
                    modifier = Modifier.clickable { /* Handle change */ }
                )
            }
            // -------------------------------------------------------

            Spacer(modifier = Modifier.height(8.dp))

            // Addresses
            addresses.forEach { address ->
                AddressCard(address = address)
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Delivery Method
            Text(
                fontFamily = SFRounded,
                text = "Delivery method.",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))

            DeliveryOptionCard(
                options = deliveryOptions,
                selectedIndex = selectedDeliveryIndex,
                onSelect = { selectedDeliveryIndex = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Total
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    fontFamily = SFRounded,
                    text = "Total",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    fontFamily = SFRounded,
                    text = totalAmount,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Proceed button
            Button(
                onClick = {
                    showPopup = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orange)
            ) {
                Text(
                    text = "Proceed to payment",
                    fontFamily = SFRounded,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
        if (showPopup) {
            PopUpScreen(
                onDismissRequest = { showPopup = false },
                onProceed = {
                    showPopup = false
                    navHostController.navigate(Routes.HomeScreen) {
                        popUpTo(Routes.HomeScreen) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun AddressCard(address: Address) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(
            1.dp,
            color = MaterialTheme.colorScheme.outline
        ), // Border wapis add kiya
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = address.name,
                fontFamily = SFRounded,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Divider(
                modifier = Modifier.padding(vertical = 6.dp),
                thickness = 0.7.dp,
                color = MaterialTheme.colorScheme.outline
            )

            Text(
                text = address.addressLine,
                fontFamily = SFRounded,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Divider(
                modifier = Modifier.padding(vertical = 6.dp),
                thickness = 0.7.dp,
                color = MaterialTheme.colorScheme.outline
            )

            Text(
                text = address.phone,
                fontFamily = SFRounded,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun DeliveryOptionCard(
    options: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(
            1.dp,
            color = MaterialTheme.colorScheme.outline
        ), // Border wapis add kiya
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            options.forEachIndexed { index, option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelect(index) }
                        .padding(vertical = 6.dp)
                ) {
                    RadioButton(
                        selected = selectedIndex == index,
                        onClick = { onSelect(index) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFFFA4A0C),
                            unselectedColor = Color.Gray
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        fontFamily = SFRounded,
                        text = option,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                if (index != options.lastIndex) {
                    Divider(
                        modifier = Modifier.padding(vertical = 6.dp),
                        thickness = 0.7.dp,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}
