package com.makhalibagas.newsq.domain.usecase

import com.makhalibagas.newsq.data.source.remote.Resource
import com.makhalibagas.newsq.domain.model.ArticlesItemModel
import com.makhalibagas.newsq.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val repository: INewsRepository) {
    operator fun invoke(): Flow<Resource<List<ArticlesItemModel>>> = repository.getNews()
}