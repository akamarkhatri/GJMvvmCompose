package com.jp.gojekassignment.gitlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jp.gojekassignment.R
import com.jp.gojekassignment.base.BaseFragment
import com.jp.gojekassignment.base.ItemClickCallbk
import com.jp.gojekassignment.data.TaskStatus
import com.jp.gojekassignment.data.model.git.GitRepo
import com.jp.gojekassignment.databinding.RepoListViewBinding
import com.jp.gojekassignment.utils.isConnectedToNetwork
import com.jp.gojekassignment.utils.showSnackbar
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GitRepListFragment: BaseFragment() {
    private lateinit var binding: RepoListViewBinding
    private val gitRepoListViewModel: GitRepoListViewModel by viewModels()
    private val itemClickCallbk: ItemClickCallbk<GitRepo> by lazy {
        object : ItemClickCallbk<GitRepo> {
            override fun onItemClick(position: Int, t: GitRepo) {

            }
        }
    }
    private val repoPagedListAdapter by lazy {
        RepoPagedListAdapter(itemClickCallbk)
    }
    private val internetErrorMsg by lazy {
        appCompatActivity.getString(R.string.msg_check_internet)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gitRepoListViewModel.fetchRepo()
    }

    override fun getFragmentView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater?.inflate(
            R.layout.repo_list_view,
            container,
            false
        )
    }

    override fun setUpView(view: View?, savedInstanceState: Bundle?) {
        observeTaskStatusLivedata()
        binding.repoRecyclerView.apply {
            adapter = repoPagedListAdapter
            layoutManager = LinearLayoutManager(
                appCompatActivity,
                RecyclerView.VERTICAL, false
            )
        }
        gitRepoListViewModel.repoPagedListLiveData.observe(this) {
            repoPagedListAdapter.submitList(it)
        }
        binding.swipeRefresh.setOnRefreshListener {
            checkNetworkConnectionAndFetchContent()
        }
        binding.errorLayout.actionRetry.setOnClickListener {
            checkNetworkConnectionAndFetchContent()
        }
    }

    private fun checkNetworkConnectionAndFetchContent() {
        if (!appCompatActivity.isConnectedToNetwork()) {
            binding.swipeRefresh.isRefreshing = false
            appCompatActivity.showSnackbar(binding.root, internetErrorMsg)
            return
        }
        gitRepoListViewModel.fetchRepo(binding.swipeRefresh.isRefreshing)
    }

    private fun observeTaskStatusLivedata() {
        gitRepoListViewModel.taskStatusLiveData.observe(this) {
            it?:return@observe
            when (it) {
                is TaskStatus.Error -> {
                    showError()
                }
                is TaskStatus.Loaded -> {
                    hideError()
                    hideProgress()
                }
                is TaskStatus.Loading -> {
                    hideError()
                    showProgress()
                }
                is TaskStatus.Refreshing -> {
                        binding.swipeRefresh.isRefreshing = true
                }
            }
        }
    }

    override fun showProgress() {
        hideError()
        binding.shimmerProgress.root.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.shimmerProgress.root.visibility = View.GONE
        binding.swipeRefresh.isRefreshing = false
    }

    override fun showError(msg: String) {
        hideProgress()
        binding.errorLayout.root.visibility = View.VISIBLE
    }

    override fun hideError() {
        binding.errorLayout.root.visibility = View.GONE
    }
}