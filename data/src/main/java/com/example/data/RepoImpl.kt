package com.example.data

import com.example.domain.Repo
import com.example.domain.models.Code
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

class RepoImpl(
    private val retrofit: Retrofit
) : Repo {
    override suspend fun getCode(login: String): Code {
        return withContext(Dispatchers.IO) {
            retrofit.api.getCode(login).toCode()
        }
    }

    override suspend fun regenerateCode(login: String) {
        return withContext(Dispatchers.IO) {
            retrofit.api.regenerateCode(login)
        }
    }

    override suspend fun getToken(login: String, password: String): ResponseBody {
        return withContext(Dispatchers.IO) {
            retrofit.api.getToken(login, password)
        }
    }
}