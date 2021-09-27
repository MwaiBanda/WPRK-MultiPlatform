package com.muse.wprk_concept.utilities

sealed class Resource<T>(
    val data:  List<T>? = null,
    val message:  String? = null
) {
    class Success<T>(data: List<T>): Resource<T>(data)
    class Error<T>(message: String, data:  List<T>? = null): Resource<T>(data, message)
    class Loading<T>(data:  List<T>? = null): Resource<T>(data)

}

sealed class ResourceObj<T>(
    val data:  T? = null,
    val message:  String? = null
) {
    class Success<T>(data: T): ResourceObj<T>(data)
    class Error<T>(message: String, data:  T? = null): ResourceObj<T>(data, message)
    class Loading<T>(data:  T? = null): ResourceObj<T>(data)

}
