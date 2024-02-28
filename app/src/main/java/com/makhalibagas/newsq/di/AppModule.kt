package com.makhalibagas.newsq.di

import com.makhalibagas.newsq.data.source.remote.repository.NewsRepository
import com.makhalibagas.newsq.domain.usecase.NewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideNewsUseCase(repository: NewsRepository) = NewsUseCase(repository)

}