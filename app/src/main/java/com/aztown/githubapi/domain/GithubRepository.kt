package com.aztown.githubapi.domain

import androidx.paging.PagingData
import com.aztown.githubapi.domain.entity.GitRepoEntity
import com.aztown.githubapi.domain.entity.GitUserEntity
import kotlinx.coroutines.flow.Flow

interface GithubRepository {

    suspend fun getPagedGithubData(query: String): Flow<PagingData<GitRepoEntity>>

    suspend fun getUserInfo(username: String): GitUserEntity

}