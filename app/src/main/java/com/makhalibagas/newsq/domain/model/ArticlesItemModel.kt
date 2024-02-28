package com.makhalibagas.newsq.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticlesItemModel(
    val publishedAt: String,
    val author: String,
    val urlToImage: String,
    val description: String,
    val source: SourceModel?,
    val title: String,
    val url: String,
    val content: String
): Parcelable

@Parcelize
data class SourceModel(
    val name: String,
    val id: String
): Parcelable
