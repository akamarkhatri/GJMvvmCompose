package com.jp.gojekassignment.di

import android.content.Context
import com.jp.gojekassignment.data.source.local.AppDatabase
import com.jp.gojekassignment.data.source.local.DaoGitRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getAppDatabase(context)
    }

    @Provides
    fun provideDaoGitRepo(appDatabase: AppDatabase): DaoGitRepo {
        return appDatabase.daoGitRepo()
    }
}