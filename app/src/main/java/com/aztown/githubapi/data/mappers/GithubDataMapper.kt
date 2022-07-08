package com.aztown.githubapi.data.mappers

import com.aztown.githubapi.data.network.models.ItemsDto
import com.aztown.githubapi.domain.entity.GithubDataEntity

class GithubDataMapper {

    fun mapDtoToEntity(dto: ItemsDto?) = GithubDataEntity(
        name = dto?.name,
        description = dto?.description,
        languages = dto?.language,
        lastChange = dto?.updatedAt,
        avatarUrl = dto?.owner?.avatarUrl,
        starsCount = dto?.stargazersCount
    )

}