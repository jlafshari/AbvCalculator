package com.example.afsharij.abvcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afsharij.abvcalculator.ui.theme.AbvCalculatorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AbvCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AbvCalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun AbvCalculatorScreen() {
    var originalGravityText by remember { mutableStateOf(TextFieldValue("")) }
    var finalGravityText by remember { mutableStateOf(TextFieldValue("")) }
    var originalGravityError by remember { mutableStateOf(false) }
    var finalGravityError by remember { mutableStateOf(false) }
    var abvResult by remember { mutableStateOf<Float?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        
        // Original Gravity Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.original_gravity),
                modifier = Modifier.padding(end = 8.dp)
            )
            OutlinedTextField(
                value = originalGravityText,
                onValueChange = { 
                    originalGravityText = it
                    originalGravityError = false
                    abvResult = null
                },
                modifier = Modifier.width(120.dp),
                singleLine = true,
                isError = originalGravityError,
                supportingText = {
                    if (originalGravityError) {
                        Text(
                            text = stringResource(R.string.invalid_original_gravity),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Final Gravity Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.final_gravity),
                modifier = Modifier.padding(end = 26.dp)
            )
            OutlinedTextField(
                value = finalGravityText,
                onValueChange = { 
                    finalGravityText = it
                    finalGravityError = false
                    abvResult = null
                },
                modifier = Modifier.width(120.dp),
                singleLine = true,
                isError = finalGravityError,
                supportingText = {
                    if (finalGravityError) {
                        Text(
                            text = stringResource(R.string.invalid_final_gravity),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Calculate Button and Result Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    val originalGravity = getFloatFromString(originalGravityText.text)
                    val finalGravity = getFloatFromString(finalGravityText.text)
                    
                    originalGravityError = originalGravity == null
                    finalGravityError = finalGravity == null
                    
                    if (originalGravity != null && finalGravity != null) {
                        abvResult = getAlcoholByVolume(originalGravity, finalGravity)
                    } else {
                        abvResult = null
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.calculate_button_text),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(50.dp))

            // ABV Result
            if (abvResult != null) {
                Text(
                    text = stringResource(R.string.abv_heading, abvResult.toString()),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

private fun getFloatFromString(text: String): Float? {
    return text.trim().toFloatOrNull()
}

private fun getAlcoholByVolume(originalGravity: Float, finalGravity: Float): Float {
    val alcoholByVolume = (1.05f * (originalGravity - finalGravity)) / (finalGravity * 0.79f) * 100
    return String.format("%.2f", alcoholByVolume).toFloat()
}
