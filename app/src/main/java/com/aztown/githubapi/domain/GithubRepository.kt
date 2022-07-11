package com.aztown.githubapi.domain

import androidx.paging.PagingData
import com.aztown.githubapi.domain.entity.GitRepoEntity
import kotlinx.coroutines.flow.Flow

interface GithubRepository {

    suspend fun getGithubData(query: String): List<GitRepoEntity>

    suspend fun getPagedGithubData(query: String):Flow<PagingData<GitRepoEntity>>

}