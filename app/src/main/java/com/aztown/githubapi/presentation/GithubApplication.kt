package com.aztown.githubapi.presentation

import android.app.Application
import com.aztown.githubapi.di.DaggerApplicationComponent

class GithubApplication : Application() {

    val component = DaggerApplicationComponent.create()

}