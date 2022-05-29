package com.jp.gojekassignment.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel(baseRepository: BaseRepository): ViewModel() {
    val taskStatusLiveData by lazy {
        baseRepository.taskStatusLiveData
    }
}