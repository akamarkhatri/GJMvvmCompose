package com.jp.gojekassignment.gitlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.jp.gojekassignment.R
import com.jp.gojekassignment.base.BaseViewHolder
import com.jp.gojekassignment.data.model.git.GitRepo
import com.jp.gojekassignment.databinding.RepoListContentItemBinding
import com.jp.gojekassignment.utils.loadThumbnailImage

class RepoPagedListAdapter(private val repoSelectedCallbk: RepoSelectedCallbk):
    PagedListAdapter<GitRepo, BaseViewHolder>(DIFF_CALLBACK) {
        companion object {
            private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GitRepo>() {
                override fun areItemsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean {
                    return oldItem == newItem
                }

            }
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val gitRepo = getItem(position)
        holder.viewBinding as RepoListContentItemBinding
        holder.viewBinding.apply {
            repoName.text = gitRepo?.name ?: ""
            description.text = gitRepo?.description ?: ""
            repoAvator.loadThumbnailImage(gitRepo?.owner?.icon)
            root.setOnClickListener {
                gitRepo?.let { repoSelectedCallbk.onItemClick(position, gitRepo) }
            }
            detailLayout.apply {
                htmlUrl.text = gitRepo?.htmlUrl ?: ""
                language.text = gitRepo?.language ?: ""
                forks.text = gitRepo?.forks?.toString() ?: ""
                stars.text = gitRepo?.watchers?.toString() ?: ""

                val languageLeftDrawable = if (gitRepo?.hasIssues == true) {
                    R.drawable.circle_drawable_green
                } else {
                    R.drawable.circle_drawable_red
                }
                language.setCompoundDrawablesWithIntrinsicBounds(
                    languageLeftDrawable,
                    0,
                    0,
                    0
                )

                root.visibility = if (repoSelectedCallbk.isPositionExpanded(position)) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = RepoListContentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BaseViewHolder(binding)
    }
}