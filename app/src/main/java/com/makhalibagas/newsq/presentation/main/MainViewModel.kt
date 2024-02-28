package com.makhalibagas.newsq.presentation.main

import androidx.lifecycle.ViewModel
import com.makhalibagas.newsq.data.source.remote.Resource
import com.makhalibagas.newsq.domain.model.ArticlesItemModel
import com.makhalibagas.newsq.domain.usecase.NewsUseCase
import com.makhalibagas.newsq.state.UiStateWrapper
import com.makhalibagas.newsq.utils.collectLifecycleFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: NewsUseCase) : ViewModel() {

    private val _newsState = MutableSharedFlow<UiStateWrapper<List<ArticlesItemModel>>>()
    val news = _newsState.asSharedFlow()

    fun getNews() {
        collectLifecycleFlow(useCase()) { resource ->
            when (resource) {
                is Resource.Loading -> _newsState.emit(UiStateWrapper.Loading(true))
                is Resource.Success -> {
                    _newsState.emit(UiStateWrapper.Loading(false))
                    _newsState.emit(UiStateWrapper.Success(resource.data))
                }

                is Resource.Error -> {
                    _newsState.emit(UiStateWrapper.Loading(false))
                    _newsState.emit(UiStateWrapper.Error(resource.msg))
                }
            }
        }
    }

}