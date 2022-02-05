package com.mwaibanda.wprksdk.features

import com.mwaibanda.wprksdk.main.usecase.auth.LoginWithEmailAndPasswordUseCase
import com.mwaibanda.wprksdk.main.usecase.auth.SigInInAsAGuestUseCase
import com.mwaibanda.wprksdk.main.usecase.auth.SignInWithEmailAndPasswordUseCase
import dev.gitlive.firebase.auth.AuthResult
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class AuthController: KoinComponent {
    private val loginWithEmailAndPasswordUseCase: LoginWithEmailAndPasswordUseCase by inject()
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase by inject()
    private val sigInInAsAGuestUseCase: SigInInAsAGuestUseCase by inject()

    suspend fun loginWithEmailAndPassword(email: String, password: String): AuthResult {
        return loginWithEmailAndPasswordUseCase(email, password)
    }

    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult {
        return signInWithEmailAndPasswordUseCase(email, password)
    }
     suspend fun sigInInAsAGuest(): AuthResult {
         return sigInInAsAGuestUseCase()
     }
}