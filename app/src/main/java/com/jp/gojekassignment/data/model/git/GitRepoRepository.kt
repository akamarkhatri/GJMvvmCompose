package com.jp.gojekassignment.data.model.git

import com.jp.gojekassignment.data.source.local.DaoGitRepo
import com.jp.gojekassignment.data.source.remote.GitRepoService
import javax.inject.Inject

class GitRepoRepository @Inject constructor(
    private val gitRepo: DaoGitRepo,
    private val gitRepoService: GitRepoService
) {
}