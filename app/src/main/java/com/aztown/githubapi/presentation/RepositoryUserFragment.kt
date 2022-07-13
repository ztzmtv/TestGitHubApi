package com.aztown.githubapi.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aztown.githubapi.databinding.FragmentRepositoryOwnerBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import javax.inject.Inject


class RepositoryUserFragment : Fragment() {

    private var _binding: FragmentRepositoryOwnerBinding? = null

    private val binding get() = _binding!!

    //private val viewModel by lazy { ViewModelProvider(this)[RepositoriesViewModel::class.java] }
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as GithubApplication).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoryOwnerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(RepositoriesViewModel::class.java)

        val username = requireArguments().getString(USERNAME)
        viewModel.getUserInfo(username)

        viewModel.userInfoLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                tvName.text = it.name
                tvBio.text = it.bio
                tvSubscribationUrl.text = it.subscriptionsUrl
                tvFollowers.text = it.followers.toString()
                Glide.with(ivAvatar)
                    .load(it.avatarUrl)
                    .apply(
                        RequestOptions().override(
                            PICTURE_SIZE_SQUARE,
                            PICTURE_SIZE_SQUARE
                        )
                    )
                    .into(ivAvatar)
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val USERNAME = "username"
        private const val PICTURE_SIZE_SQUARE = 216

        fun newInstance(username: String) = RepositoryUserFragment().apply {
            arguments = Bundle().apply {
                putString(USERNAME, username)
            }
        }
    }
}