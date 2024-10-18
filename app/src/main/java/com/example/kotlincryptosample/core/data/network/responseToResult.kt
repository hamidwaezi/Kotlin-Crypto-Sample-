package com.example.kotlincryptosample.core.data.network

import com.example.kotlincryptosample.core.domain.util.Result

import com.example.kotlincryptosample.core.domain.util.NetworkError
import com.example.kotlincryptosample.core.domain.util.fail
import com.example.kotlincryptosample.core.domain.util.success
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.SerializationException

suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, NetworkError> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                success(response.body<T>())
            } catch (ex: SerializationException) {
                fail(NetworkError.Serialization(ex))
            } catch (ex: NoTransformationFoundException) {
                fail(NetworkError.Serialization(ex))
            }
        }

        408 -> fail(NetworkError.RequestTimeOut(null))
        429 -> fail(NetworkError.TooManyRequests(null))
        in 500..599 -> fail(NetworkError.ServerError(response as? Exception))
        else -> fail(NetworkError.Unknown(null))
    }
}