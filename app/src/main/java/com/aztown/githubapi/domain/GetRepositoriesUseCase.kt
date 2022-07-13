package com.aztown.githubapi.domain

import androidx.paging.PagingData
import com.aztown.githubapi.domain.entity.GitRepoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(query: String): Flow<PagingData<GitRepoEntity>> {
        return repository.getPagedGithubData(query)
    }
}