package com.jp.gojekassignment.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.jp.gojekassignment.data.model.git.GitRepo

@Dao
interface DaoGitRepo:BaseDao<GitRepo> {
    @Query("select * from GitRepo")
    fun getAllGitRepoLiveData():LiveData<List<GitRepo>>
}