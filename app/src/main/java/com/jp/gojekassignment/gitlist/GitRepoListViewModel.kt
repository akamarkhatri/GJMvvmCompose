package com.jp.gojekassignment.gitlist

import androidx.lifecycle.ViewModel
import com.jp.gojekassignment.data.model.git.GitRepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GitRepoListViewModel @Inject internal  constructor(gitRepoRepository: GitRepoRepository):ViewModel() {
    val repoListLiveData by lazy {

    }
}