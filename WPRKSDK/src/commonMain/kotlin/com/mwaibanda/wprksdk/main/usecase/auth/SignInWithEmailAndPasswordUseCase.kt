package com.mwaibanda.wprksdk.main.usecase.auth

import com.mwaibanda.wprksdk.main.repository.AuthRepository
import dev.gitlive.firebase.auth.AuthResult

class SignInWithEmailAndPasswordUseCase (
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): AuthResult {
        return authRepository.signInWithEmailAndPassword(email, password)
    }
}