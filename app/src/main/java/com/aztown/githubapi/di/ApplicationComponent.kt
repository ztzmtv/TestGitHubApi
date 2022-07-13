package com.aztown.githubapi.di

import com.aztown.githubapi.presentation.RepositoriesListFragment
import dagger.Component

@Component
interface ApplicationComponent {

    fun inject(repositoriesListFragment: RepositoriesListFragment)

}