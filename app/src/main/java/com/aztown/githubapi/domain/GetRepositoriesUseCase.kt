package com.aztown.githubapi.domain

import androidx.paging.PagingData
import com.aztown.githubapi.domain.entity.GitRepoEntity
import kotlinx.coroutines.flow.Flow

class GetRepositoriesUseCase(private val repository: GithubRepository) {

    suspend operator fun invoke(query: String): Flow<PagingData<GitRepoEntity>> {
        return repository.getPagedGithubData(query)
    }

}