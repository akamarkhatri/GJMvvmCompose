package com.jp.gojekassignment.gitlist

import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jp.gojekassignment.base.BaseViewModel
import com.jp.gojekassignment.data.model.git.GitRepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitRepoListViewModel @Inject internal  constructor(private val gitRepoRepository: GitRepoRepository):BaseViewModel(gitRepoRepository) {
    private val repoPageListConfig by lazy {
        PagedList.Config.Builder()
            .setPageSize(100)
            .setPrefetchDistance(100)
            .setEnablePlaceholders(false)
            .build()
    }
    val repoPagedListLiveData by lazy {
        LivePagedListBuilder(
            gitRepoRepository.getGitRepoDataSource(),
            repoPageListConfig
        ).build()
    }
    fun fetchRepo(isRefreshing: Boolean = false){
        viewModelScope.launch {
            gitRepoRepository.fetchRepo(isRefreshing)
        }
    }
}