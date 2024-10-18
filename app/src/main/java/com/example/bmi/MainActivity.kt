package com.example.bmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmi.ui.theme.BmiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BmiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BmiCalculatorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun BmiCalculatorScreen(modifier: Modifier = Modifier, viewModel: BmiViewModel = viewModel()) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = viewModel.height.value,
            onValueChange = { viewModel.updateHeight(it) },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            decorationBox = { innerTextField ->
                Box(Modifier.padding(8.dp)) {
                    if (viewModel.height.value.isEmpty()) Text("Height")
                    innerTextField()
                }
            }
        )

        BasicTextField(
            value = viewModel.weight.value,
            onValueChange = { viewModel.updateWeight(it) },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            decorationBox = { innerTextField ->
                Box(Modifier.padding(8.dp)) {
                    if (viewModel.weight.value.isEmpty()) Text("Weight")
                    innerTextField()
                }
            }
        )

        Text(
            text = "Body mass index is: ${"%.2f".format(viewModel.bmi.value)}",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BmiCalculatorScreenPreview() {
    BmiTheme {
        BmiCalculatorScreen()
    }
}
