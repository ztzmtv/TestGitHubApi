package com.aztown.githubapi.domain

import com.aztown.githubapi.domain.entity.GitUserEntity

class GetUserInfoUseCase(private val repository: GithubRepository) {

    suspend operator fun invoke(username: String): GitUserEntity {
        return repository.getUserInfo(username)
    }

}
