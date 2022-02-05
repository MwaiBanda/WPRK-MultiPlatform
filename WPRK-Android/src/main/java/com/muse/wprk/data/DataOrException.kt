package com.muse.wprk.data

data class DataOrException<T, E : Exception>(
    val data: T? = null,
    val e: E? = null
)
