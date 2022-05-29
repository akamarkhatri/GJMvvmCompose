package com.jp.gojekassignment.gitlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jp.gojekassignment.base.BaseViewModel
import com.jp.gojekassignment.data.model.git.GitRepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitRepoListViewModel @Inject internal  constructor(private val gitRepoRepository: GitRepoRepository):BaseViewModel(gitRepoRepository) {
    val repoListLiveData by lazy {

    }
    fun fetchRepo(isRefreshing: Boolean = false){
        viewModelScope.launch {
            gitRepoRepository.fetchRepo(isRefreshing)
        }
    }
}