package com.jp.gojekassignment.data

sealed class TaskStatus(
    val taskId: TaskId,
    val taskState: TaskState,
    val response: Any? = null,
    val msg: String? = null
) {
    class Loading(taskId: TaskId) : TaskStatus(taskId, TaskState.LOADING)
    class Error(taskId: TaskId, msg: String?) : TaskStatus(taskId, TaskState.FAILED, msg = msg)
    class Loaded(taskId: TaskId, response: Any?= null) : TaskStatus(taskId, TaskState.LOADED, response)
    class Refreshing(taskId: TaskId) : TaskStatus(taskId, TaskState.REFRESHING)
}

enum class TaskState {
    FAILED,
    LOADING,
    LOADED,
    REFRESHING
}

enum class TaskId {
    FETCH_REPO
}
