package com.mwaibanda.wprksdk.controller

import com.mwaibanda.wprksdk.di.RepositoryModule
import com.mwaibanda.wprksdk.di.SingletonModule
import com.mwaibanda.wprksdk.di.UseCaseModule
import com.mwaibanda.wprksdk.main.usecase.auth.LoginWithEmailAndPasswordUseCase
import com.mwaibanda.wprksdk.main.usecase.auth.SignInInAsAGuestUseCase
import com.mwaibanda.wprksdk.main.usecase.auth.SignInWithEmailAndPasswordUseCase
import dev.gitlive.firebase.auth.AuthResult
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance


open class AuthController: DIAware {
    override val di: DI by DI.lazy {
        import(SingletonModule)
        import(RepositoryModule)
        import(UseCaseModule)
    }
    private val loginWithEmailAndPasswordUseCase: LoginWithEmailAndPasswordUseCase by instance()
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase by instance()
    private val signInInAsAGuestUseCase: SignInInAsAGuestUseCase by instance()

    suspend fun loginWithEmailAndPassword(email: String, password: String): AuthResult {
        return loginWithEmailAndPasswordUseCase(email, password)
    }

    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult {
        return signInWithEmailAndPasswordUseCase(email, password)
    }

     suspend fun sigInInAsAGuest(): AuthResult {
         return signInInAsAGuestUseCase()
     }
}