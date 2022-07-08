package com.aztown.githubapi.domain

import com.aztown.githubapi.domain.entity.GithubDataEntity

interface GithubRepository {

    suspend fun getGithubData(query: String): List<GithubDataEntity>

}