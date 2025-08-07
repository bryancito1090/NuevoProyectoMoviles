package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorApp()
        }
    }
}

@Composable
fun BMICalculatorApp() {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    val bmi = calculateBMI(weight, height)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text("Peso (kg)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                label = { Text("Altura (m)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "BMI: ${bmi ?: "--"}")
        }
    }
}

fun calculateBMI(weight: String, height: String): String? {
    val w = weight.toFloatOrNull()
    val h = height.toFloatOrNull()
    if (w != null && h != null && h > 0f) {
        val bmi = w / (h * h)
        return String.format("%.2f", bmi)
    }
    return null
}
