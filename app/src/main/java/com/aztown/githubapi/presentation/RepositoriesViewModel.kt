package com.aztown.githubapi.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aztown.githubapi.data.GithubRepositoryImpl
import com.aztown.githubapi.domain.entity.GitRepoEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest


@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class RepositoriesViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = GithubRepositoryImpl()

    private val queryLiveData = MutableLiveData("")

    var gitRepoFlow: Flow<PagingData<GitRepoEntity>>

    init {
        gitRepoFlow =
            queryLiveData
                .asFlow()
                .debounce(500)
                .flatMapLatest { repository.getPagedGithubData(it) }
                .cachedIn(viewModelScope)
    }

    fun load(query: String) {
        if (this.queryLiveData.value == query) return
        this.queryLiveData.value = query
    }
}