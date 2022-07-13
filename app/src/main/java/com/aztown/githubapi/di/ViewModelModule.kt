package com.aztown.githubapi.di

import androidx.lifecycle.ViewModel
import com.aztown.githubapi.presentation.RepositoriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RepositoriesViewModel::class)
    fun bindRepositoriesViewModel(viewModel: RepositoriesViewModel): ViewModel

}