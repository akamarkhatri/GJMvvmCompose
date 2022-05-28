package com.jp.gojekassignment.data.source.remote

import com.jp.gojekassignment.data.model.git.GitRepo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitRepoService {
    @GET("/{orgName}/repos")
    suspend fun getRepo(@Path("orgName") org: String = "octokit", @Query("type") type: String = "public"):Deferred<List<GitRepo>>
}