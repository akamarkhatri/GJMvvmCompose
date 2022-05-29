package com.jp.gojekassignment.gitlist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jp.gojekassignment.R
import com.jp.gojekassignment.base.BaseFragment
import com.jp.gojekassignment.base.ItemClickCallbk
import com.jp.gojekassignment.data.TaskStatus
import com.jp.gojekassignment.data.model.git.GitRepo
import com.jp.gojekassignment.databinding.FragmentRepoListBinding
import com.jp.gojekassignment.utils.isConnectedToNetwork
import com.jp.gojekassignment.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitRepListFragment: BaseFragment() {
    private lateinit var binding: FragmentRepoListBinding
    private val gitRepoListViewModel: GitRepoListViewModel by viewModels()

    private val repoPagedListAdapter by lazy {
        RepoPagedListAdapter(gitRepoListViewModel)
    }
    private val internetErrorMsg by lazy {
        appCompatActivity.getString(R.string.msg_check_internet)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gitRepoListViewModel.fetchRepo()
    }

    override fun getFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val binding = FragmentRepoListBinding.inflate(inflater, container, false)
        binding = FragmentRepoListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_repository_list, menu)
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
        gitRepoListViewModel.expandedPositionDetailLiveData.observe(this) {
            it?:return@observe
            it.prevPosition?.let {
                repoPagedListAdapter.notifyItemChanged(it)
            }
            it.currentPosition?.let { 
                repoPagedListAdapter.notifyItemChanged(it)
            }
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