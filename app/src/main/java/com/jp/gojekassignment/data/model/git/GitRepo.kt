package com.jp.gojekassignment.data.model.git

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.jp.gojekassignment.data.source.local.RoomDataTypeConvertor

@Entity
data class GitRepo(
    @PrimaryKey
    val id: Int,
    val name: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @TypeConverters(RoomDataTypeConvertor::class)
    val owner: RepoOwner,
    val language: String?,
    val forks: Int?,
    val watchers: Int?
)

data class RepoOwner(
    val id: Int,
    @SerializedName("avatar_url")
    val icon: String?
)