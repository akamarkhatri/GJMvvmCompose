package com.jp.gojekassignment.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    lateinit var appCompatActivity: AppCompatActivity

    final override fun onAttach(context: Context) {
        super.onAttach(context)
        appCompatActivity = context as AppCompatActivity
    }

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getFragmentView(inflater,container,savedInstanceState)
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(view,savedInstanceState)
    }

    abstract fun getFragmentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
    abstract fun setUpView(view: View?, savedInstanceState: Bundle?)
    abstract fun showProgress()
    abstract fun hideProgress()
    abstract fun showError(msg:String="")
    abstract fun hideError()
}