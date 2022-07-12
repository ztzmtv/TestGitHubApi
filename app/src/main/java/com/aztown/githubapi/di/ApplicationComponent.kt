package com.aztown.githubapi.di

import com.aztown.githubapi.presentation.MainActivity
import com.aztown.githubapi.presentation.RepositoriesListFragment
import com.aztown.githubapi.presentation.RepositoryUserFragment
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class]
)

interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(repositoriesListFragment: RepositoriesListFragment)

    fun inject(repositoryUserFragment: RepositoryUserFragment)

}