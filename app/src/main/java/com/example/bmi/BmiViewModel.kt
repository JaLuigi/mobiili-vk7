package com.example.bmi

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BmiViewModel : ViewModel() {
    var height = mutableStateOf("")
        private set
    var weight = mutableStateOf("")
        private set
    var bmi = mutableStateOf(0.0)
        private set

    fun updateHeight(newHeight: String) {
        height.value = newHeight
        calculateBmi()
    }

    fun updateWeight(newWeight: String) {
        weight.value = newWeight
        calculateBmi()
    }

    private fun calculateBmi() {
        val heightValue = height.value.toDoubleOrNull() ?: 0.0
        val weightValue = weight.value.toDoubleOrNull() ?: 0.0
        if (heightValue > 0 && weightValue > 0) {
            bmi.value = weightValue / (heightValue * heightValue)
        } else {
            bmi.value = 0.0
        }
    }
}


