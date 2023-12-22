@file:JvmName("SecondStepKt")

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testlogindelo.viewModel.GetCodeViewModel

@Composable
fun EnterSms(
    phoneNumber: String,
    viewModel: GetCodeViewModel,
    onClick: () -> Unit
) {
    var sms by rememberSaveable {
        mutableStateOf("")
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.align(Alignment.Center)) {

                Text(
                    text = "Код был отправлен на номер телефона \n+$phoneNumber",
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
                PhoneField(
                    phone = sms,
                    mask = "0-0-0-0-0-0",
                    onPhoneChanged = {
                        sms = it
                    },
                    label = {
                        Text(text = "СМС")
                    },
                    modifier = Modifier
                        .width(328.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 96.dp)
                )
                Button(
                    onClick = {
                        viewModel.getToken(phoneNumber, sms)
                        onClick()
                    },
                    modifier = Modifier
                        .width(328.dp)
                        .align(Alignment.CenterHorizontally),
                    enabled = sms.length == 6
                ) {
                    Text(text = "Продолжить")
                }
            }
        }
    }
}