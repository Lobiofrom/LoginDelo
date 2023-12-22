package com.example.domain

import okhttp3.ResponseBody

class GetTokenUseCase(
    private val repo: Repo
) {
    suspend fun execute(login: String, password: String): ResponseBody {
        return repo.getToken(login, password)
    }
}