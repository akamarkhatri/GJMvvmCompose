package com.jp.gojekassignment.data.model.git

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.jp.gojekassignment.data.source.local.RoomDataTypeConvertor

@Entity
data class GitRepo(
    @PrimaryKey
    var id: Int,
    var name: String?,
    @SerializedName("full_name")
    var fullName: String?,
    @TypeConverters(RoomDataTypeConvertor::class)
    var owner: RepoOwner,
    var language: String?,
    var forks: Int?,
    var watchers: Int?
)

data class RepoOwner(
    val id: Int,
    @SerializedName("avatar_url")
    val icon: String?
)