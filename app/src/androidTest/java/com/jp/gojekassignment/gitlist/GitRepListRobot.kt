package com.jp.gojekassignment.gitlist

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.jp.gojekassignment.R
import com.jp.gojekassignment.test.BaseRobot
import com.jp.gojekassignment.test.withRecyclerView
import org.hamcrest.CoreMatchers.not

fun gitRepoList(func: GitRepListRobot.() -> Unit) = GitRepListRobot().apply(func)

class GitRepListRobot : BaseRobot(){
    fun verifyProgressViewDisplayed() {
        onView(ViewMatchers.withId(R.id.shimmer_progress))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun verifyContentIsExpand(contentPosition: Int) {
        onView(withRecyclerView(R.id.repo_recycler_view).atPositionOnView(contentPosition, R.id.detail_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun verifyContentIsCollapsed(contentPosition: Int) {
        onView(withRecyclerView(R.id.repo_recycler_view).atPositionOnView(contentPosition, R.id.detail_layout))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))
    }

    fun clickContent(contentPosition: Int) {
        clickItemInRecyclerView(R.id.repo_recycler_view, contentPosition)
    }
}