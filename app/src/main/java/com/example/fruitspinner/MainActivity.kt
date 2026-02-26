package com.example.fruitspinner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                FruitSpinner()
            }
        }
    }
}

@Composable
fun FruitSpinner() {

    var expanded by remember { mutableStateOf(false) }

    val options = listOf(
        FruitItem(R.drawable.banana, "Banana"),
        FruitItem(R.drawable.apple, "Apple"),
        FruitItem(R.drawable.mango, "Mango")
    )

    var selectedOption by remember { mutableStateOf(options[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).padding(top = 20.dp)
    ) {

        Box {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = selectedOption.name,
                onValueChange = {},
                readOnly = true,
                label = { Text("Select a Fruit") },
                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown"
                        )
                    }
                }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { fruit ->
                    DropdownMenuItem(
                        text = { Text(fruit.name) },
                        onClick = {
                            selectedOption = fruit
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = selectedOption.imageRes),
                contentDescription = selectedOption.name,
                modifier = Modifier.size(220.dp)
            )
        }
    }
}

data class FruitItem(
    val imageRes: Int,
    val name: String
)