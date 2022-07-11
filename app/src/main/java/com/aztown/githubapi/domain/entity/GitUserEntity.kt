package com.aztown.githubapi.domain.entity

data class GitUserEntity(
    val name: String? = null,
    val bio: String? = null,
    val avatarUrl: String? = null,
    val subscriptionsUrl: String? = null,
    val followers: Int? = null,
)
