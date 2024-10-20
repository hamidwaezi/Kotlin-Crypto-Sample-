package com.example.kotlincryptosample.core.domain.util


sealed interface Result<out D, out E : Error> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Fail<out E : Error>(val error: E) : Result<Nothing, E>
}

inline fun <T, E : Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when (this) {
        is Result.Success -> Result.Success(map(data))
        is Result.Fail -> Result.Fail(error)
    }
}

inline fun <T, E : Error> Result<T, E>.onSuccess(h: (T) -> Unit): Result<T, E> {
    if (this is Result.Success) {
        h(data)
    }
    return this;
}

inline fun <T, E : Error> Result<T, E>.onFail(h: (E) -> Unit): Result<T, E> {
    if (this is Result.Fail) {
        h(error)
    }
    return this
}

typealias success<D> = Result.Success<D>
typealias fail<E> = Result.Fail<E>

