package com.aztown.githubapi.data.mappers

import com.aztown.githubapi.data.network.models.GitUserDto
import com.aztown.githubapi.data.network.models.ItemsDto
import com.aztown.githubapi.domain.entity.GitRepoEntity
import com.aztown.githubapi.domain.entity.GitUserEntity

class GithubDataMapper {
    fun mapItemDtoToEntity(dto: ItemsDto?) = GitRepoEntity(
        id = dto?.id,
        name = dto?.name,
        description = dto?.description,
        languages = dto?.language,
        lastChange = dto?.updatedAt,
        avatarUrl = dto?.owner?.avatarUrl,
        starsCount = dto?.stargazersCount,
        ownerUsername = dto?.owner?.login
    )

    fun mapUserDtoToEntity(dto: GitUserDto?) = GitUserEntity(
        name = dto?.name,
        bio = dto?.bio,
        avatarUrl = dto?.avatarUrl,
        subscriptionsUrl = dto?.subscriptionsUrl,
        followers = dto?.followers
    )
}