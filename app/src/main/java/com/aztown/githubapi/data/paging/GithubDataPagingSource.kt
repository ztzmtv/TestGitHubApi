package com.aztown.githubapi.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aztown.githubapi.data.mappers.GithubDataMapper
import com.aztown.githubapi.data.network.ApiService
import com.aztown.githubapi.domain.entity.GitRepoEntity
import retrofit2.HttpException

class GithubDataPagingSource(
    private val searchQuery: String,
    private val apiService: ApiService
) : PagingSource<Int, GitRepoEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitRepoEntity> {
        val mapper = GithubDataMapper()
        val pageIndex = params.key ?: FIRST_PAGE_NUMBER
        return try {
            val response = apiService.getListOfRepositories(
                q = searchQuery,
                page = pageIndex
            )
            val items = response.items
            val githubData = mutableListOf<GitRepoEntity>()
            items?.let {
                it.forEach { itemDto ->
                    val itemEntity = mapper.mapItemDtoToEntity(itemDto)
                    githubData.add(itemEntity)
                }
            }
            LoadResult.Page(
                data = githubData,
                prevKey = if (pageIndex == FIRST_PAGE_NUMBER) null else pageIndex - 1,
                nextKey = if (githubData.size == params.loadSize) pageIndex + 1 else null
            )
        } catch (e: Exception) {
            if (e is HttpException && e.code() == 401)
                Log.d("Exception_TAG", e.stackTraceToString())
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GitRepoEntity>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 30
        private const val FIRST_PAGE_NUMBER = 1
    }

}
