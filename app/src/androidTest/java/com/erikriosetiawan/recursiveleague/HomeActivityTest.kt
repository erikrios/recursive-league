package com.erikriosetiawan.recursiveleague

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.erikriosetiawan.recursiveleague.activities.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testAppBehaviour() {
        onView(withId(R.id.item_search))
            .check(matches(isDisplayed()))
        onView(withId(R.id.item_search)).perform(click())
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(
            typeText("Arsenal vs Tottenham"),
            pressImeActionButton()
        )
        Thread.sleep(10000)
        onView(withId(R.id.rv_match_search)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_match_search)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                15
            )
        )
        onView(withId(R.id.rv_match_search)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(15, click())
        )
    }
}