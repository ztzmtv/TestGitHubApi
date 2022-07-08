package com.aztown.githubapi.data

import android.util.Log
import com.aztown.githubapi.data.mappers.GithubDataMapper
import com.aztown.githubapi.data.network.ApiFactory
import com.aztown.githubapi.domain.GithubRepository
import com.aztown.githubapi.domain.entity.GithubDataEntity

class GithubRepositoryImpl() : GithubRepository {
    private val apiService = ApiFactory.apiService

    override suspend fun getGithubData(query: String): List<GithubDataEntity> {
        val mapper = GithubDataMapper()
        val resultList = mutableListOf<GithubDataEntity>()
        val response = apiService.getListOfRepositories(query)
        val items = response.items
        if (items != null) {
            for (item in items) {
                resultList += mapper.mapDtoToEntity(item)
            }
        }
        Log.d("GithubRepositoryImpl_TAG", resultList.toString())
        return resultList
    }
}