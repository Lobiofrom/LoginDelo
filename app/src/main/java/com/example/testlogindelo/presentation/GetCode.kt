package com.example.testlogindelo.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testlogindelo.viewModel.GetCodeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun GetCode(
    viewModel: GetCodeViewModel = koinViewModel()
) {
    var phoneNumber by rememberSaveable {
        mutableStateOf("")
    }

    var code by remember {
        mutableStateOf<com.example.domain.models.Code?>(null)
    }

    var showGetCode by remember {
        mutableStateOf(true)
    }
    var showSms by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        viewModel.code.collect {
            code = it
        }
    }
    if (code?.status == "old") {
        showGetCode = false

    }
    if (showGetCode) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    PhoneField(
                        phone = phoneNumber,
                        mask = "+0 (000) 000-00-00",
                        onPhoneChanged = {
                            phoneNumber = it
                        },
                        label = {
                            Text(text = "номер телефона")
                        },
                        modifier = Modifier
                            .width(328.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 96.dp)
                    )
                    Button(
                        onClick = {
                            viewModel.getCode(phoneNumber)
                            showSms = true
                            showGetCode = false
                        },
                        modifier = Modifier
                            .width(328.dp)
                            .align(Alignment.CenterHorizontally),
                        enabled = phoneNumber.length == 11
                    ) {
                        Text(text = "Продолжить")
                    }
                }
            }
        }
    }
    if (showSms) {
        SecondScreen(phoneNumber = phoneNumber)
    }
}