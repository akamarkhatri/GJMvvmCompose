package com.jp.gojekassignment.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jp.gojekassignment.data.TaskStatus

abstract class BaseRepository {
    val taskStatusLiveData: LiveData<TaskStatus?> by lazy {
        MutableLiveData<TaskStatus>()
    }
    fun updateTaskStatus(taskStatus: TaskStatus) {
        (taskStatusLiveData as MutableLiveData).postValue(taskStatus)
    }
}