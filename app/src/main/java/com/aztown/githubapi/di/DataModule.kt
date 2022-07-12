package com.aztown.githubapi.di

import com.aztown.githubapi.data.GithubRepositoryImpl
import com.aztown.githubapi.domain.GithubRepository
import dagger.Binds
import dagger.Module


@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindsRepository(impl: GithubRepositoryImpl): GithubRepository

}