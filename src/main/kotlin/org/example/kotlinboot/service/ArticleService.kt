package org.example.kotlinboot.service

import org.example.kotlinboot.model.Article
import org.example.kotlinboot.repository.ArticleRepository
import org.springframework.stereotype.Service

@Service
class ArticleService (
    private val articleRepository : ArticleRepository
){
    fun findAll() = articleRepository.findAll()
}