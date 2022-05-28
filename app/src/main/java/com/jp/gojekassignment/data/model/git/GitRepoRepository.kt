package com.jp.gojekassignment.data.model.git

import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import com.jp.gojekassignment.data.TaskId
import com.jp.gojekassignment.data.TaskStatus
import com.jp.gojekassignment.data.model.BaseRepository
import com.jp.gojekassignment.data.source.local.DaoAppConfig
import com.jp.gojekassignment.data.source.local.DaoGitRepo
import com.jp.gojekassignment.data.source.remote.GitRepoService
import javax.inject.Inject

class GitRepoRepository @Inject constructor(
    private val daoGitRepo: DaoGitRepo,
    private val gitRepoService: GitRepoService,
    private val daoAppConfig: DaoAppConfig
):BaseRepository() {
    private val CACHE_EXPIRES_TIME=2* DateUtils.HOUR_IN_MILLIS
    fun getGitRepoListLivedata(): LiveData<List<GitRepo>> {
        return daoGitRepo.getAllGitRepoLiveData()
    }

    suspend fun fetchRepo(isRefreshing: Boolean) {
        if(isRefreshing) {
            updateTaskStatus(TaskStatus.Refreshing(TaskId.FETCH_REPO))
        } else {
            if(!canFetchRepoFromServer())
                return
            updateTaskStatus(TaskStatus.Loading(TaskId.FETCH_REPO))
        }
        val repoList = gitRepoService.getRepo().await()
        daoGitRepo.insert(repoList)
        updateTaskStatus(TaskStatus.Loaded(TaskId.FETCH_REPO))
        daoAppConfig.updateRepoLastUpdateTime(System.currentTimeMillis())
    }

    private fun canFetchRepoFromServer(): Boolean {
        return System.currentTimeMillis() - daoAppConfig.getRepoLastUpdateTime() >= CACHE_EXPIRES_TIME
    }

}