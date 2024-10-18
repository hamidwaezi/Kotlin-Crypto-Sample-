package com.example.kotlincryptosample.core.data.network

import com.example.kotlincryptosample.core.domain.util.NetworkError
import com.example.kotlincryptosample.core.domain.util.Result
import com.example.kotlincryptosample.core.domain.util.fail
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, NetworkError> {
    val response: HttpResponse = try {
        execute()
    } catch (ex: UnresolvedAddressException) {
        return fail(NetworkError.NoInternet(ex))
    } catch (ex: SerializationException) {
        return fail(NetworkError.Serialization(ex))
    } catch (ex: Exception) {
        coroutineContext.ensureActive()
        return fail(NetworkError.Unknown(ex))
    }

    return responseToResult(response)
}