package com.aztown.githubapi.domain.entity

data class GitRepoEntity(
    val name: String? = null,
    val description: String? = null,
    val languages: String? = null,
    val lastChange: String? = null,
    val avatarUrl: String? = null,
    val starsCount: Int? = null
)