package com.example.testlogindelo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.States
import com.example.domain.GetCodeUseCase
import com.example.domain.GetTokenUseCase
import com.example.domain.RegenerateCodeUseCase
import com.example.domain.models.Code
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class GetCodeViewModel(
    private val useCase: GetCodeUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val regenerateCodeUseCase: RegenerateCodeUseCase
) : ViewModel() {

    private var _code = MutableStateFlow<Code?>(null)
    val code = _code.asStateFlow()

    private var _token = MutableStateFlow<ResponseBody?>(null)
    val token = _token.asStateFlow()

    private var _state = MutableStateFlow<States>(States.Initial)
    val state = _state.asStateFlow()

    fun getCode(login: String) {
        _state.value = States.Loading
        viewModelScope.launch {
            _code.value = useCase.execute(login)
            _state.value = States.Initial
        }
    }

    fun regenerateCode(login: String) {
        _state.value = States.Loading
        viewModelScope.launch {
            regenerateCodeUseCase.execute(login)
            _state.value = States.Initial
        }
    }

    fun getToken(login: String, password: String) {
        _state.value = States.Loading
        viewModelScope.launch {
            try {
                _token.value = getTokenUseCase.execute(login, password)
                _state.value = States.Success

            } catch (e: Exception) {
                _state.value = States.Error
            }
        }
    }
}