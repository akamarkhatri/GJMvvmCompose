package com.jp.gojekassignment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jp.gojekassignment.BuildConfig

@Entity
data class AppConfig(
    @PrimaryKey
    val id: String = BuildConfig.APPLICATION_ID,
    val repoLastUpdateTime: Long
)