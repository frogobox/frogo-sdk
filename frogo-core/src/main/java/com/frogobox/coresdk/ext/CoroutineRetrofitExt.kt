package com.frogobox.coresdk.ext

import com.frogobox.coresdk.source.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

/**
 * Created by faisalamircs on 01/10/2025
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 */


/**
 * Executes a Retrofit suspend call and emits [Resource] states as a [Flow] on [Dispatchers.IO].
 */
fun <T> fetchAsFlow(call: suspend () -> Response<T>): Flow<Resource<T?>> = flow {
    try {
        emit(Resource.Loading())
        val response = call()
        if (response.isSuccessful) {
            emit(Resource.Success(response.body()))
        } else {
            val errorMessage = response.message().ifEmpty { "HTTP Error ${response.code()}" }
            emit(Resource.Error(code = response.code(), message = errorMessage))
        }
    } catch (e: Exception) {
        emit(Resource.Error(code = 500, message = e.localizedMessage ?: "Unknown Error"))
    }
}.flowOn(Dispatchers.IO)