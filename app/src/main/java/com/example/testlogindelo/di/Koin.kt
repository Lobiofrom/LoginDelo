package com.example.testlogindelo.di

import com.example.data.RepoImpl
import com.example.data.Retrofit
import com.example.domain.GetCodeUseCase
import com.example.domain.GetTokenUseCase
import com.example.domain.RegenerateCodeUseCase
import com.example.domain.Repo
import com.example.testlogindelo.viewModel.GetCodeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repo> { RepoImpl(Retrofit()) }

    factory { GetCodeUseCase(get()) }
    factory { GetTokenUseCase(get()) }
    factory { RegenerateCodeUseCase(get()) }

    viewModel { GetCodeViewModel(get(), get(), get()) }

}
