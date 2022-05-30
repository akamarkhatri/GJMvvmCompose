package com.jp.gojekassignment.idlingresources

import androidx.test.espresso.IdlingResource
import com.jp.gojekassignment.BuildConfig

object EspressoIdlingResources {
    private const val resource = "GLOBAL_IDLING_RESOURCE"
    private val countingIdlingResource = SimpleCountingIdlingResources(resource)

    fun increment() {
        if (BuildConfig.DEBUG)
            countingIdlingResource.increment()
    }

    fun decrement() {
        try {
            if (BuildConfig.DEBUG)
                countingIdlingResource.decrement()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getIdlingResource(): IdlingResource = countingIdlingResource
}
