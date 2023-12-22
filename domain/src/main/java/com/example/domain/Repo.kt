package com.example.domain

import com.example.domain.models.Code
import okhttp3.ResponseBody

interface Repo {
    suspend fun getCode(login: String): Code
    suspend fun regenerateCode(login: String)
    suspend fun getToken(login: String, password: String): ResponseBody
}