package com.aztown.githubapi.data.network

import com.aztown.githubapi.data.network.models.GitRepoDto
import com.aztown.githubapi.data.network.models.GitUserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //НЕ НАЧИНАТЬ С СИМВОЛА "/"

    @GET("search/repositories")
    suspend fun getListOfRepositories(
        @Query(QUERY_PARAM_Q) q: String,
        @Query(QUERY_PARAM_PAGE) page: Int = DEFAULT_PAGE
    ): GitRepoDto

    @GET("users/{username}")
    suspend fun getRepoOwnerInfo(
        @Path("username") username: String
    ): GitUserDto

    companion object {
        private const val QUERY_PARAM_Q = "q"
        private const val QUERY_PARAM_PAGE = "page"
        private const val DEFAULT_PAGE = 1
    }
}