package com.mwaibanda.wprksdk.data.remote

import com.mwaibanda.wprksdk.main.repository.AuthRepository
import dev.gitlive.firebase.auth.AuthCredential
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseAuth

class AuthRepositoryImpl(
    private val auth: FirebaseAuth
): AuthRepository {
    override suspend fun loginWithEmailAndPassword(email: String, password: String): AuthResult {
        return auth.signInWithEmailAndPassword(email, password)
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    override suspend fun loginAsGuest(): AuthResult {
       return auth.signInAnonymously()
    }

    override suspend fun loginWithCredential(credential: AuthCredential): AuthResult {
        return auth.signInWithCredential(credential)
    }

    override suspend fun resetPassword(email: String) {
        auth.sendPasswordResetEmail(email)
    }

}