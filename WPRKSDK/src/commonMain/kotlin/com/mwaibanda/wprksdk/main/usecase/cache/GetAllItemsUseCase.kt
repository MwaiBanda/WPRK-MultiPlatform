package com.mwaibanda.wprksdk.main.usecase.cache

import com.mwaibanda.wprksdk.util.CacheControl

class GetAllItemsUseCase<out T: Any> (
    private val cacheControl: CacheControl
){
    operator fun invoke(prefix: String = ""): List<T> {
        return cacheControl.getAllItems(prefix)
    }
}