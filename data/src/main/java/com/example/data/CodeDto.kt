package com.example.data

import com.example.domain.models.Code

data class CodeDto(
    val code: String,
    val status: String
)

fun CodeDto.toCode() = Code(
    code = code,
    status = status
)