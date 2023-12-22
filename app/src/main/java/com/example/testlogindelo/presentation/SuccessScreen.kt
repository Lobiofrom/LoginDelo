package com.example.testlogindelo.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import okhttp3.ResponseBody

@Composable
fun SuccessScreen(
    phoneNumber: String,
    response: ResponseBody?
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Здравствуйте, $phoneNumber, ваш токен:\n ${response?.string()}",
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )
    }
}