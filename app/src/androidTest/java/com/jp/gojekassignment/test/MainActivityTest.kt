package com.jp.gojekassignment.test

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.jp.gojekassignment.MainActivity
import com.jp.gojekassignment.R
import com.jp.gojekassignment.data.source.local.AppDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Rule
import org.junit.rules.RuleChain

abstract class MainActivityTest : BaseTest() {
     val hiltRule = HiltAndroidRule(this)
     val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(activityScenarioRule)
}