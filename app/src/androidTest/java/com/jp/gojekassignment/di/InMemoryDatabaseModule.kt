package com.jp.gojekassignment.di

import android.content.Context
import com.jp.gojekassignment.data.source.local.AppDatabase
import com.jp.gojekassignment.data.source.local.DaoAppConfig
import com.jp.gojekassignment.data.source.local.DaoGitRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
class InMemoryDatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getAppDatabase(context, true)
    }

    @Provides
    fun provideDaoGitRepo(appDatabase: AppDatabase): DaoGitRepo {
        return appDatabase.daoGitRepo()
    }

    @Provides
    fun providesDaoAppConfig(appDatabase: AppDatabase) : DaoAppConfig {
        return appDatabase.daoAppConfig()
    }
}