package com.aztown.githubapi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aztown.githubapi.R
import com.aztown.githubapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            replaceFragmentInContainer(RepositoriesListFragment.newInstance())
        }

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuitem_github_list -> {
                    replaceFragmentInContainer(RepositoriesListFragment.newInstance())
                    true
                }
                R.id.menuitem_github_about -> {
                    replaceFragmentInContainer(AboutAppFragment.newInstance())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragmentInContainer(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}