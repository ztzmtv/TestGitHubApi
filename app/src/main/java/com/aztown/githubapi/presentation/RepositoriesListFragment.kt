package com.aztown.githubapi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aztown.githubapi.databinding.FragmentRepositoriesListBinding
import com.aztown.githubapi.presentation.adapter.GithubListAdapter

class RepositoriesListFragment : Fragment() {

    private var _binding: FragmentRepositoriesListBinding? = null

    private val binding get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this)[RepositoriesViewModel::class.java] }

    private var githubAdapter: GithubListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoriesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.rvGithubList

        viewModel.gitHubListLiveData.observe(viewLifecycleOwner) {
            githubAdapter = GithubListAdapter(it)
            recyclerView.adapter = githubAdapter
        }


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.loadData(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.loadData(newText)
                return true
            }

        })

        savedInstanceState?.getString(SEARCH_STRING)?.let {
            binding.searchView.setQuery(it, false)
            binding.searchView.clearFocus()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val searchString = binding.searchView.query
        outState.putString(searchString.toString(), SEARCH_STRING)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val SEARCH_STRING = "search_string"
        fun newInstance(): Fragment {
            return RepositoriesListFragment()
        }
    }
}