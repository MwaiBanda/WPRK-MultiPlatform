package com.mwaibanda.wprksdk.main.usecase.cache

import com.mwaibanda.wprksdk.util.CacheControl

class SetItemUseCase<in T: Any>(
    private val cacheControl: CacheControl
) {
    operator fun invoke(key: String, value: T) {
        cacheControl.setItem(key = key, value = value)
    }
}