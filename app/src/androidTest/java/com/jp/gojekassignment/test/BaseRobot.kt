package com.jp.gojekassignment.test

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions

abstract class BaseRobot {
    fun clickItemInRecyclerView(recyclerViewId: Int, itemPosition: Int) {
        Espresso.onView(withRecyclerView(recyclerViewId).atPosition(itemPosition))
            .perform(ViewActions.click())
    }
}