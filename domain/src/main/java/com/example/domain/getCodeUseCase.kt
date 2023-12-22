package com.example.domain

import com.example.domain.models.Code

class GetCodeUseCase(
    private val repo: Repo
) {
    suspend fun execute(login: String): Code {
        return repo.getCode(login)
    }
}