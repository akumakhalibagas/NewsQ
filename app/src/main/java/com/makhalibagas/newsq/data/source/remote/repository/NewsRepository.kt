package com.makhalibagas.newsq.data.source.remote.repository

import com.makhalibagas.newsq.data.source.remote.Resource
import com.makhalibagas.newsq.data.source.remote.NewsRemoteDataSource
import com.makhalibagas.newsq.data.source.remote.network.ApiResponse
import com.makhalibagas.newsq.domain.model.ArticlesItemModel
import com.makhalibagas.newsq.domain.repository.INewsRepository
import com.makhalibagas.newsq.utils.DataMapper.toArticles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val remoteDataSource: NewsRemoteDataSource) :
    INewsRepository {

    override fun getNews(): Flow<Resource<List<ArticlesItemModel>>> {
        return flow {
            emit(Resource.Loading)
            when (val apiResource = remoteDataSource.getNews().first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(apiResource.data.articles?.map {
                        it.toArticles()
                    } ?: emptyList()))
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResource.msg))
                }
            }
        }
    }
}