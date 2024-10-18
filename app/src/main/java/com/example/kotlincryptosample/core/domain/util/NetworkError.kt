package com.example.kotlincryptosample.core.domain.util


sealed interface NetworkError : Error {
    val exception: Exception?

    class RequestTimeOut(override val exception: Exception?) : NetworkError
    class TooManyRequests(override val exception: Exception?) : NetworkError
    class NoInternet(override val exception: Exception?) : NetworkError
    class ServerError(override val exception: Exception?) : NetworkError
    class Serialization(override val exception: Exception?) : NetworkError
    class Unknown(override val exception: Exception?) : NetworkError
}