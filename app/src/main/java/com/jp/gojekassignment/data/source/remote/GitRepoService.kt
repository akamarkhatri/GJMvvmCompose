package com.jp.gojekassignment.data.source.remote

import com.jp.gojekassignment.data.model.git.GitRepo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitRepoService {
    @GET("/{orgName}/repos")
    suspend fun getRepo(@Path("orgName") org: String, @Query("type") type: String = "public"):List<GitRepo>
}