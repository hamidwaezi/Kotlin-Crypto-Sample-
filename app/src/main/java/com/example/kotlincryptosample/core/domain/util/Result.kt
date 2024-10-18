package com.example.kotlincryptosample.core.domain.util


sealed interface Result<out D, out E : Error> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Fail<out E : Error>(val error: E) : Result<Nothing, E>
}

typealias success<D> = Result.Success<D>
typealias fail<E> = Result.Fail<E>
