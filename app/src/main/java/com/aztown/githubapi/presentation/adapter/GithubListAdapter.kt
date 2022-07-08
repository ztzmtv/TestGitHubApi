package com.aztown.githubapi.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aztown.githubapi.databinding.RvItemRepositoryBinding
import com.aztown.githubapi.domain.entity.GithubDataEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GithubListAdapter(
    private val list: List<GithubDataEntity>
) : RecyclerView.Adapter<GithubListAdapter.GithubListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubListViewHolder {
        val binding = RvItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GithubListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GithubListViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding) {
            tvName.text = item.name ?: DEFAULT_STRING
            tvDescriotion.text = item.description ?: DEFAULT_STRING
            tvLastChangesDate.text = item.lastChange ?: DEFAULT_STRING
            tvStarsCount.text = item.starsCount.toString()
            Glide.with(ivAvatar)
                .load(item.avatarUrl)
                .apply(RequestOptions().override(PICTURE_SIZE_SQUARE, PICTURE_SIZE_SQUARE))
                .into(ivAvatar)
        }
    }

    override fun getItemCount() = list.size

    inner class GithubListViewHolder(
        val binding: RvItemRepositoryBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val DEFAULT_STRING = "Unknown"
        private const val PICTURE_SIZE_SQUARE = 216
    }
}