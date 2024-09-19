package org.example.kotlinboot.repository

import org.example.kotlinboot.model.Article
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ArticleRepository {

    private val articles = listOf(
        Article(id = UUID.randomUUID(), title = "article 1", content = "content 1"),
        Article(id = UUID.randomUUID(), title = "article 2", content = "content 2")
    )
    fun findAll(): List<Article> = articles
}