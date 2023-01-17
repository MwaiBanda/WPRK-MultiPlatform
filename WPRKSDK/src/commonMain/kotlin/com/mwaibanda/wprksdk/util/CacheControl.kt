package com.mwaibanda.wprksdk.util

interface CacheControl {
    fun <T: Any> getItem(key: String): T?
    fun <T: Any> setItem(key: String, value: T)
    fun <T: Any> getAllItems(prefix: String): List<T>
}