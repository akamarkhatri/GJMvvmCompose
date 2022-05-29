package com.jp.gojekassignment.gitlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jp.gojekassignment.R
import com.jp.gojekassignment.base.BaseFragment
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GitRepListFragment: BaseFragment() {
    private val gitRepoListViewModel: GitRepoListViewModel by viewModels()

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
        TODO("Not yet implemented")
    }

    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun hideProgress() {
        TODO("Not yet implemented")
    }

    override fun showError(msg: String) {
        TODO("Not yet implemented")
    }

    override fun hideError() {
        TODO("Not yet implemented")
    }
}