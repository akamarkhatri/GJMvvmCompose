package com.jp.gojekassignment.gitlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.jp.gojekassignment.R
import com.jp.gojekassignment.base.BaseViewHolder
import com.jp.gojekassignment.base.ItemClickCallbk
import com.jp.gojekassignment.data.model.git.GitRepo

class RepoPagedListAdapter(val itemClickCallbk: ItemClickCallbk<GitRepo>):
    PagingDataAdapter<GitRepo, BaseViewHolder>(DIFF_CALLBACK) {
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
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.repo_list_content_item,
            parent,
            false
        )
        return BaseViewHolder(itemLayout)
    }
}