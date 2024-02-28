package com.makhalibagas.newsq.data.source.remote

import com.makhalibagas.newsq.data.source.remote.network.ApiResponse
import com.makhalibagas.newsq.data.source.remote.network.NewsApiService
import com.makhalibagas.newsq.data.source.remote.response.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRemoteDataSource @Inject constructor(private val apiService: NewsApiService) {

    fun getNews(): Flow<ApiResponse<NewsResponse>> =
        flow {
            try {
                val response = apiService.getNews()
                if (response != null) {
                    emit(ApiResponse.Success(response))
                    return@flow
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

}