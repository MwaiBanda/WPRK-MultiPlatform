package com.muse.wprk.data.repository

import com.mwaibanda.wprksdk.util.CacheControl

class MockCacheRepositoryImpl: CacheControl {
    private val inMemoryCache = hashMapOf<String, List<Any>?>()

    override fun <T : Any> getItem(key: String): T? {
        TODO("Not yet implemented")
    }

    override fun <T : Any> setItem(key: String, value: T) {
        TODO("Not yet implemented")
    }

    override fun <T : Any> getAllItems(prefix: String): List<T> {
        TODO("Not yet implemented")
    }

}