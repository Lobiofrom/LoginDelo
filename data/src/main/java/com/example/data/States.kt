package com.example.data

sealed class States {
    data object Success : States()
    data object Error : States()
    data object Initial : States()
    data object Loading : States()
}
