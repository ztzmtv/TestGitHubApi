package com.aztown.githubapi.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aztown.githubapi.data.GithubRepositoryImpl
import com.aztown.githubapi.domain.entity.GithubDataEntity
import kotlinx.coroutines.launch

class RepositoriesViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = GithubRepositoryImpl()

    private val _gitHubListLiveData = MutableLiveData<List<GithubDataEntity>>()

    val gitHubListLiveData: LiveData<List<GithubDataEntity>>
        get() = _gitHubListLiveData

    fun loadData(query: String?) {
        if ((query != null) && (query.length >= 4)) {
            viewModelScope.launch {
                _gitHubListLiveData.value = repository.getGithubData(query)
            }
        }

    }

}