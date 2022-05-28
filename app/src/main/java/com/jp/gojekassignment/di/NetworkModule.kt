package com.jp.gojekassignment.di

import com.jp.gojekassignment.data.ServerInfo
import com.jp.gojekassignment.data.source.remote.GitRepoService
import com.jp.gojekassignment.data.source.remote.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideServerInfo(): ServerInfo {
        return ServerInfo.Prod
    }

    @Singleton
    @Provides
    fun provideNetworkClient(serverInfo: ServerInfo):NetworkClient {
        return NetworkClient(serverInfo)
    }

    @Provides
    fun provideGitRepoService(networkClient: NetworkClient): GitRepoService {
        return networkClient.getClient().create(GitRepoService::class.java)
    }
}