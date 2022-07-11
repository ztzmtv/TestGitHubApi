package com.aztown.githubapi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.aztown.githubapi.R
import com.aztown.githubapi.databinding.FragmentRepositoriesListBinding
import com.aztown.githubapi.presentation.adapter.GithubListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RepositoriesListFragment : Fragment() {

    private var _binding: FragmentRepositoriesListBinding? = null

    private val binding get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this)[RepositoriesViewModel::class.java] }

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
        val githubAdapter = GithubListAdapter()
        recyclerView.adapter = githubAdapter

        githubAdapter.onRepoClickListener = { username ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, RepositoryUserFragment.newInstance(username))
                .addToBackStack(null)
                .commit()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.gitRepoFlow.collectLatest {
                githubAdapter.submitData(it)
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query != null) {
                    viewModel.load(query)
                    true
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Запрос пустой. Введите запрос",
                        Toast.LENGTH_SHORT
                    ).show()
                    false
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.load(newText)
                return true
            }

        })

//TODO(hmm, think about it)
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

        fun newInstance(): RepositoriesListFragment {
            return RepositoriesListFragment()
        }
    }
}