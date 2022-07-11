package com.aztown.githubapi.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aztown.githubapi.data.mappers.GithubDataMapper
import com.aztown.githubapi.data.network.ApiFactory
import com.aztown.githubapi.data.paging.GithubDataPagingSource
import com.aztown.githubapi.domain.GithubRepository
import com.aztown.githubapi.domain.entity.GitRepoEntity
import kotlinx.coroutines.flow.Flow

class GithubRepositoryImpl() : GithubRepository {
    private val apiService = ApiFactory.apiService

    override suspend fun getPagedGithubData(query: String): Flow<PagingData<GitRepoEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = GithubDataPagingSource.DEFAULT_PAGE_SIZE,
                initialLoadSize = GithubDataPagingSource.DEFAULT_PAGE_SIZE
            ),
            pagingSourceFactory = { GithubDataPagingSource(query, apiService) },
        ).flow
    }


}