package com.mwaibanda.wprksdk.di

import com.mwaibanda.wprksdk.data.remote.AuthRepositoryImpl
import com.mwaibanda.wprksdk.main.repository.AuthRepository
import com.mwaibanda.wprksdk.main.usecase.auth.LoginWithEmailAndPasswordUseCase
import com.mwaibanda.wprksdk.main.usecase.auth.SignInInAsAGuestUseCase
import com.mwaibanda.wprksdk.main.usecase.auth.SignInWithEmailAndPasswordUseCase
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            singletonModule,
            repositoryModule,
            useCasesModule,
        )
    }

// IOS
fun initKoin() = initKoin {
    modules(
        singletonModule,
        repositoryModule,
        useCasesModule,
    )
}

val singletonModule = module {
    single { Firebase.auth }
}
val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}
val useCasesModule = module {
    single { LoginWithEmailAndPasswordUseCase(get()) }
    single { SignInInAsAGuestUseCase(get()) }
    single { SignInWithEmailAndPasswordUseCase(get()) }
}

