package com.jp.gojekassignment.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jp.gojekassignment.R
import com.jp.gojekassignment.data.model.git.GitRepo


@Database(entities = [GitRepo::class], version = 1)
@TypeConverters(RoomDataTypeConvertor::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var appDatabase: AppDatabase? = null

        @Volatile
        private var appDatabaseInMemory: AppDatabase? = null

        fun getAppDatabase(context: Context, inMemory: Boolean = false): AppDatabase {
            if (inMemory) {
                synchronized(this) {
                    if (appDatabaseInMemory == null) {
                        appDatabaseInMemory = Room.inMemoryDatabaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                    return appDatabaseInMemory!!
                }
            }
            synchronized(this) {
                if (appDatabase == null) {
                    appDatabase = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        context.getString(R.string.app_name)
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return appDatabase!!
            }

        }
    }
    abstract fun daoGitRepo():DaoGitRepo
}