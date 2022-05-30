package com.jp.gojekassignment.gitlist

import com.jp.gojekassignment.test.MainActivityTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class GitRepoListFragmentTest : MainActivityTest() {

    @Test
    fun checkProgressIsShown() {
        gitRepoList {
            verifyProgressViewDisplayed()
        }
    }

    @Test
    fun verifyContentIsExpandAndCollapseOnClick() {
        gitRepoList {
            Thread.sleep(5000)
            clickContent(1)
            verifyContentIsExpand(1)
            clickContent(1)
            verifyContentIsCollapsed(1)
            clickContent(2)
            clickContent(3)
            verifyContentIsExpand(3)
            verifyContentIsCollapsed(2)
        }
    }
}