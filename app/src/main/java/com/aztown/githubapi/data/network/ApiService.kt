package com.aztown.githubapi.data.network

import com.aztown.githubapi.data.network.models.GithubDataDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //НЕ НАЧИНАТЬ С СИМВОЛА "/"

    @GET("search/repositories")
    suspend fun getListOfRepositories(
        @Query(QUERY_PARAM_Q) per_page: String = DEFAULT_Q,
    ): GithubDataDto

    companion object {
        private const val QUERY_PARAM_Q = "q"
        private const val DEFAULT_Q = "yandex"
    }
}