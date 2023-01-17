package com.mwaibanda.wprksdk.di

import com.mwaibanda.wprksdk.util.CacheControl
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import io.github.reactivecircus.cache4k.Cache
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import com.mwaibanda.wprksdk.data.cache.CacheControl as CacheControlImpl

val SingletonModule = DI.Module("SDK/Singleton") {
    bind<HttpClient>()  with singleton {
        HttpClient {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
            }
            install(HttpTimeout) {
                val timeout = 30000L
                connectTimeoutMillis = timeout
                requestTimeoutMillis = timeout
                socketTimeoutMillis = timeout
            }
        }
    }
    bind<Cache<String, Any>>() with singleton {
        Cache.Builder()
          .expireAfterWrite(1.hours)
          .build()
    }
    bind<FirebaseAuth>() with singleton { Firebase.auth }
    bind<CacheControl>() with singleton { CacheControlImpl(cache = instance()) }
}
