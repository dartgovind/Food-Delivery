package com.example.fooddelivery.presentation.paymentscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddelivery.R
import com.example.fooddelivery.navigation.Routes
import com.example.fooddelivery.ui.theme.SFRounded

// --- Enums to hold the selection states ---
enum class PaymentOption { Card, BankAccount }
enum class DeliveryOption { DoorDelivery, PickUp }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(navHostController: NavHostController) {
    var selectedPayment by remember { mutableStateOf(PaymentOption.Card) }
    var selectedDelivery by remember { mutableStateOf(DeliveryOption.DoorDelivery) }

    Surface(color = MaterialTheme.colorScheme.background) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Checkout",
                            fontFamily = SFRounded,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navHostController.popBackStack()
                            },
                            modifier = Modifier.padding(start = 16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    actions = {
                        // Agar fav ya kuch aur icon add karna ho
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
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Payment",
                    fontFamily = SFRounded,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Payment method section
                PaymentMethodSection(
                    selectedOption = selectedPayment,
                    onOptionSelected = { selectedPayment = it }
                )
                Spacer(modifier = Modifier.height(32.dp))

                // Delivery method section
                DeliveryMethodSection(
                    selectedOption = selectedDelivery,
                    onOptionSelected = { selectedDelivery = it }
                )

                Spacer(modifier = Modifier.height(48.dp))

                // Total amount
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total",
                        fontFamily = SFRounded,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "23,000",
                        fontFamily = SFRounded,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        navHostController.navigate(Routes.DeliveryScreen)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFA4A0C)
                    ),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text(
                        fontFamily = SFRounded,
                        text = "Proceed to payment",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun PaymentMethodSection(
    selectedOption: PaymentOption,
    onOptionSelected: (PaymentOption) -> Unit
) {
    Text(
        text = "Payment method",
        fontFamily = SFRounded,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onBackground
    )
    Spacer(modifier = Modifier.height(12.dp))

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            // Card Option
            PaymentSelectionItem(
                text = "Card",
                icon = {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(
                                color = Color(0xFFFFA726),
                                shape = RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.card),
                            contentDescription = "Card Icon",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                isSelected = selectedOption == PaymentOption.Card,
                onClick = { onOptionSelected(PaymentOption.Card) },
                showDivider = true
            )

            // Bank Option
            PaymentSelectionItem(
                text = "Bank account",
                icon = {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(
                                color = Color(0xFF9C27B0),
                                shape = RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.bank),
                            contentDescription = "Bank Icon",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                isSelected = selectedOption == PaymentOption.BankAccount,
                onClick = { onOptionSelected(PaymentOption.BankAccount) },
                showDivider = false
            )
        }
    }
}


@Composable
fun PaymentSelectionItem(
    text: String,
    icon: @Composable () -> Unit,
    isSelected: Boolean,
    onClick: () -> Unit,
    showDivider: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFFFA4A0C), // Fixed orange
                unselectedColor = Color.Gray       // Optional, gray unselected
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = Modifier.size(36.dp)) { icon() }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = SFRounded,
            color = MaterialTheme.colorScheme.onBackground
        )
    }

    if (showDivider) {
        Divider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = Color.LightGray,
            thickness = 1.dp
        )
    }
}

@Composable
fun DeliveryMethodSection(
    selectedOption: DeliveryOption,
    onOptionSelected: (DeliveryOption) -> Unit
) {
    Text(
        text = "Delivery method",
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onBackground
    )
    Spacer(modifier = Modifier.height(12.dp))

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            SelectionItem(
                text = "Door delivery",
                isSelected = selectedOption == DeliveryOption.DoorDelivery,
                onClick = { onOptionSelected(DeliveryOption.DoorDelivery) },
                showDivider = true
            )
            SelectionItem(
                text = "Pick up",
                isSelected = selectedOption == DeliveryOption.PickUp,
                onClick = { onOptionSelected(DeliveryOption.PickUp) },
                showDivider = false
            )
        }
    }
}

@Composable
fun SelectionItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    showDivider: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFFFA4A0C), // Fixed orange
                unselectedColor = Color.Gray       // Optional, gray unselected
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontFamily = SFRounded,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }

    if (showDivider) {
        Divider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = Color.LightGray,
            thickness = 1.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    PaymentScreen(rememberNavController())
}
