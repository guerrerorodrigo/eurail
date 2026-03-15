package com.rodrigoguerrero.shared.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    tableName = "article_details",
    foreignKeys = [
        ForeignKey(
            entity = ArticleEntity::class,
            parentColumns = ["id"],
            childColumns = ["articleId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class ArticleDetailsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val articleId: Int,
    val content: String,
    val createdAt: Long,
)

data class ArticleAndDetails(
    @Embedded val article: ArticleEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "articleId",
    )
    val details: ArticleDetailsEntity?,
)
