package com.mwaibanda.wprksdk.main.usecase.cache

import com.mwaibanda.wprksdk.util.CacheControl

class GetItemUseCase<out T: Any> (
    private val cacheControl: CacheControl
){
    operator fun invoke(key: String): T? {
        return cacheControl.getItem(key = key)
    }
}