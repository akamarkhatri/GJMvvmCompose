package com.jp.gojekassignment.data.source.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.jp.gojekassignment.data.model.git.GitRepo

@Dao
interface DaoGitRepo:BaseDao<GitRepo> {
    @Query("select * from GitRepo")
    fun getAllGitRepoDataSource(): DataSource.Factory<Int, GitRepo>

}