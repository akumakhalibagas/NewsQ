package com.makhalibagas.newsq.data.source.remote.network

import com.makhalibagas.newsq.BuildConfig
import com.makhalibagas.newsq.data.source.remote.response.NewsResponse
import retrofit2.http.GET

interface NewsApiService {

    @GET("top-headlines?country=id&apiKey=${BuildConfig.API_KEY}")
    suspend fun getNews(): NewsResponse
}