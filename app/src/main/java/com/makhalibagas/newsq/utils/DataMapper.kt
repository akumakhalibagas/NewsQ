package com.makhalibagas.newsq.utils

import com.makhalibagas.newsq.data.source.remote.response.ArticlesItem
import com.makhalibagas.newsq.data.source.remote.response.Source
import com.makhalibagas.newsq.domain.model.ArticlesItemModel
import com.makhalibagas.newsq.domain.model.SourceModel

object DataMapper {

    fun ArticlesItem.toArticles() = ArticlesItemModel(
        publishedAt.toString(),
        author.toString(),
        urlToImage.toString(),
        description.toString(),
        source?.toSource(),
        title.toString(),
        url.toString(),
        content.toString()
    )

    fun Source.toSource() = SourceModel(
        name.toString(), id.toString()
    )
}