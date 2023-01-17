package com.mwaibanda.wprksdk.data.cache

import com.mwaibanda.wprksdk.util.CacheControl
import io.github.reactivecircus.cache4k.Cache

internal class CacheControl(
    private val cache: Cache<String, Any>
) : CacheControl {
    override fun <T : Any> getItem(key: String): T? {
        return cache.get(key = key) as T?
    }

    override fun <T : Any> setItem(key: String, value: T) {
        cache.put(key = key, value = value)
    }

    override fun <T : Any> getAllItems(prefix: String): List<T> {
        return cache.asMap()
            .filter { it.key.toString().startsWith(prefix) }
            .map { it.value as T }
    }
}