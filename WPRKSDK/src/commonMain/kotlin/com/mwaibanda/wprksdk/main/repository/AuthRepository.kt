package com.mwaibanda.wprksdk.main.repository

import dev.gitlive.firebase.auth.AuthCredential
import dev.gitlive.firebase.auth.AuthResult

interface AuthRepository {
    suspend fun loginWithEmailAndPassword(email: String, password: String): AuthResult
    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult
    suspend fun loginAsGuest(): AuthResult
    suspend fun loginWithCredential(credential: AuthCredential): AuthResult
    suspend fun resetPassword(email: String)
}