package com.jp.gojekassignment.test

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jp.gojekassignment.idlingresources.EspressoIdlingResources
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseTest {
    private val idlingResources = EspressoIdlingResources.getIdlingResource()

    @Before
    fun baseTest_registerIdlingResource() {
       IdlingRegistry.getInstance().register(idlingResources)
    }

    @After
    fun baseTest_unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(idlingResources)
    }
}