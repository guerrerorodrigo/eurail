package com.rodrigoguerrero.eurail.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDetailsDto(
    val id: Int,
    val title: String,
    val content: String,
    @SerialName("updated_date")
    val updatedDate: String,
)
