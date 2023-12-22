package com.example.testlogindelo.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.data.States
import com.example.testlogindelo.viewModel.GetCodeViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.koin.androidx.compose.koinViewModel

@Composable
fun SecondScreen(
    phoneNumber: String,
    viewModel: GetCodeViewModel = koinViewModel()
) {
    var response by remember {
        mutableStateOf<ResponseBody?>(null)
    }

    var state by remember {
        mutableStateOf<States>(States.Initial)
    }

    LaunchedEffect(Unit) {
        viewModel.state.collect {
            state = it
        }
    }

    LaunchedEffect(Unit) {
        viewModel.token.collect {
            response = it
        }
    }
    val snackState = remember { SnackbarHostState() }

    val snackScope = rememberCoroutineScope()


    when (state) {
        States.Success -> {
            SuccessScreen(phoneNumber = phoneNumber, response = response)
        }

        States.Error -> {
            ErrorScreen(
                snackState = snackState,
                phoneNumber = phoneNumber,
                viewModel = viewModel
            )
        }

        States.Initial -> {
            EnterSms(
                phoneNumber = phoneNumber,
                viewModel = viewModel,
                onClick = {
                    snackScope.launch {
                        snackState.showSnackbar("Неверный код")
                    }
                })
        }

        States.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}