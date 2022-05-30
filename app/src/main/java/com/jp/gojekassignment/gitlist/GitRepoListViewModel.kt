package com.jp.gojekassignment.gitlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jp.gojekassignment.base.BaseViewModel
import com.jp.gojekassignment.data.model.git.GitRepo
import com.jp.gojekassignment.data.model.git.GitRepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitRepoListViewModel @Inject internal  constructor(private val gitRepoRepository: GitRepoRepository):BaseViewModel(gitRepoRepository), RepoSelectedCallbk {
    private val repoPageListConfig by lazy {
        PagedList.Config.Builder()
            .setPageSize(100)
            .setPrefetchDistance(100)
            .setEnablePlaceholders(false)
            .build()
    }
    val repoPagedListLiveData by lazy {
        LivePagedListBuilder(
            gitRepoRepository.getGitRepoDataSource(),
            repoPageListConfig
        ).build()
    }
    fun fetchRepo(isRefreshing: Boolean = false){
        viewModelScope.launch (Dispatchers.IO){
            gitRepoRepository.fetchRepo(isRefreshing)
        }
    }
    val expandedPositionDetailLiveData: LiveData<ExpandedPositionDetail?> by lazy {
        MutableLiveData<ExpandedPositionDetail?>()
    }

    override fun isPositionExpanded(position: Int): Boolean {
        return expandedPositionDetailLiveData.value?.currentPosition == position
    }

    override fun onItemClick(position: Int, t: GitRepo) {
        val currentDetail = expandedPositionDetailLiveData.value

        val updatedDetail = when {
            currentDetail == null -> {
                ExpandedPositionDetail(currentPosition = position)
            }
            currentDetail.currentPosition == position -> {
                ExpandedPositionDetail(prevPosition = currentDetail.currentPosition)
            }
            else -> {
                ExpandedPositionDetail(
                    prevPosition = currentDetail.currentPosition,
                    currentPosition = position
                )
            }
        }
        (expandedPositionDetailLiveData as MutableLiveData).postValue(updatedDetail)
    }

}
data class ExpandedPositionDetail(val prevPosition: Int? = null, val currentPosition: Int? = null)