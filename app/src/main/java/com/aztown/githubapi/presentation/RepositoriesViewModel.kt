package com.aztown.githubapi.presentation

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aztown.githubapi.data.GithubRepositoryImpl
import com.aztown.githubapi.domain.GetRepositoriesUseCase
import com.aztown.githubapi.domain.GetUserInfoUseCase
import com.aztown.githubapi.domain.entity.GitRepoEntity
import com.aztown.githubapi.domain.entity.GitUserEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch


@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class RepositoriesViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repositoryImpl = GithubRepositoryImpl()

    private val getRepositoriesUseCase = GetRepositoriesUseCase(repositoryImpl)

    private val getUserInfoUseCase = GetUserInfoUseCase(repositoryImpl)

    private val queryLiveData = MutableLiveData(DEFAULT_EMPTY_STRING)

    private val _userInfoLiveData = MutableLiveData<GitUserEntity>()

    val userInfoLiveData: LiveData<GitUserEntity>
        get() = _userInfoLiveData

    var gitRepoFlow: Flow<PagingData<GitRepoEntity>>

    init {
        gitRepoFlow =
            queryLiveData
                .asFlow()
                .debounce(TIMEOUT_IN_MILLIS)
                .flatMapLatest { getRepositoriesUseCase(it) }
                .cachedIn(viewModelScope)
    }

    fun load(query: String?) {
        query ?: return
        if (this.queryLiveData.value == query) return
        this.queryLiveData.value = query
    }

    fun getUserInfo(username: String?) {
        username ?: return
        viewModelScope.launch {
            _userInfoLiveData.value = getUserInfoUseCase(username)
        }

    }

    companion object {
        private const val DEFAULT_EMPTY_STRING = ""
        private const val TIMEOUT_IN_MILLIS = 500L
    }

}