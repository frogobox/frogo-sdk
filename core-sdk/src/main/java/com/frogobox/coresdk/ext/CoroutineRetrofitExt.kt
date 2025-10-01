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
 * Github   : github.com/amirisback
 * -----------------------------------------
 */


/**
 *     // Search meal by name
 *     fun searchMeal(apiKey: String, nameMeal: String): Flow<Resource<MealResponse<MealModel>?>> =
 *         flow {
 *             try {
 *                 emit(Resource.Loading())
 *                 val request = apiService.searchMeal(apiKey, nameMeal)
 *                 val response = request.body()
 *                 if (!request.isSuccessful) {
 *                     emit(Resource.Error(request.message()))
 *                 } else {
 *                     emit(Resource.Success(response))
 *                 }
 *             } catch (e: Exception) {
 *                 emit(Resource.Error(e.message.toString()))
 *             }
 *         }.flowOn(Dispatchers.IO)
 */