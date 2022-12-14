package com.jp.gojekassignment.data.source.local

import androidx.room.Dao
import androidx.room.Query
import com.jp.gojekassignment.data.model.AppConfig

@Dao
interface DaoAppConfig : BaseDao<AppConfig> {

    @Query("select repoLastUpdateTime from AppConfig Limit 1")
    suspend fun getRepoLastUpdateTime():Long

    @Query("update AppConfig set repoLastUpdateTime=:repoLastUpdateTime")
    suspend fun updateRepoLastUpdateTime(repoLastUpdateTime:Long)

    @Query("select * from AppConfig Limit 1")
    suspend fun getAppConfig(): AppConfig?
}