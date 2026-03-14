package com.rodrigoguerrero.eurail.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    val id: Int,
    val title: String,
    val summary: String,
    @SerialName("updated_date")
    val updatedDate: String,
)
