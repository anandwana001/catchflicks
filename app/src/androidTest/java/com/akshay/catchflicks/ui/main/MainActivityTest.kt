package com.akshay.catchflicks.ui.main

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import com.akshay.catchflicks.R
import com.akshay.catchflicks.TestComponentRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

/**
 * Created by akshaynandwana on
 * 07, January, 2020
 **/
class MainActivityTest {

    private val component =
        TestComponentRule(InstrumentationRegistry.getInstrumentation().targetContext)

    private val main = IntentsTestRule(MainActivity::class.java, false, false)

    @get:Rule
    val chain = RuleChain.outerRule(component).around(main)

    @Before
    fun setup() {

    }

    /**
     * Testing, does our bottomNav is visible or not by launching MainActivity
     * onView, withId, check, matches and isDisplayed are the part of espresso
     */
    @Test
    fun testCheckViewsDisplayed() {
        main.launchActivity(Intent(component.getContext(), MainActivity::class.java))
        onView(withId(R.id.bottomNavigation))
            .check(matches(isDisplayed()))
    }
}