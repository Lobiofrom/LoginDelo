package com.example.domain

class RegenerateCodeUseCase(
    private val repo: Repo
) {
    suspend fun execute(login: String) {
        return repo.regenerateCode(login)
    }
}