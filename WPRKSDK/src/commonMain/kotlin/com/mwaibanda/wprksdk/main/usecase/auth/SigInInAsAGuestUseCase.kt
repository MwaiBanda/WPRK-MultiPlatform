package com.mwaibanda.wprksdk.main.usecase.auth

import com.mwaibanda.wprksdk.main.repository.AuthRepository
import dev.gitlive.firebase.auth.AuthResult

class SigInInAsAGuestUseCase (
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke():AuthResult {
        return authRepository.loginAsGuest()
    }
}