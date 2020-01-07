package com.akshay.catchflicks

import android.content.Context
import com.akshay.catchflicks.di.component.DaggerTestComponent
import com.akshay.catchflicks.di.component.TestComponent
import com.akshay.catchflicks.di.module.ApplicationTestModule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Created by akshaynandwana on
 * 07, January, 2020
 **/

/**
 * Rule needed to setup dagger
 */
class TestComponentRule(private val context: Context) : TestRule {

    private var testComponent: TestComponent? = null

    fun getContext() = context

    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                try {
                    setupDaggerTestComponentInApplication()
                    base.evaluate()
                } finally {
                    testComponent = null
                }
            }
        }
    }

    private fun setupDaggerTestComponentInApplication() {
        val application = context.applicationContext as CatchflicksApplication
        testComponent = DaggerTestComponent.builder()
            .applicationTestModule(ApplicationTestModule(application))
            .build()
        application.setComponent(testComponent!!)
    }
}