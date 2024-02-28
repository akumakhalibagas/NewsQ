package com.makhalibagas.newsq.domain.repository

import com.makhalibagas.newsq.data.source.remote.Resource
import com.makhalibagas.newsq.domain.model.ArticlesItemModel
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    fun getNews(): Flow<Resource<List<ArticlesItemModel>>>
}