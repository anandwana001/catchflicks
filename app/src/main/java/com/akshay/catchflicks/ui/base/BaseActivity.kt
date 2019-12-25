package com.akshay.catchflicks.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.akshay.catchflicks.CatchflicksApplication
import com.akshay.catchflicks.di.component.ActivityComponent
import com.akshay.catchflicks.di.component.DaggerActivityComponent
import com.akshay.catchflicks.di.module.ActivityModule
import javax.inject.Inject

/**
 * Created by akshaynandwana on
 * 25, December, 2019
 **/

/**
 * abstract becuase this class cannot be used unless we extend it.
 * Any class extend this class has to implement all abstract methods and properties.
 * If we need to override non-abstract method, then we have to mark it open.
 * We cannot crete object of abstract class.
 *
 * We are using Generics here. Whichever activity extends this class has to specify its viewModel class
 * and that will be passed here as a parameter in (VM) and viewModel var will store the reference of it.
 *
 * injectDependencies() is the abstract method which will be implmented at subclass.
 * Dagger builder work is done here, all we have to do in subclass is to pass the context to inject.
 *
 * setContentView() which we use to set the layout file. We have provideLayoutId() method,
 * we have to pass the layout file id and the complete setContentView work will be done here in BaseActivity class.
 **/

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setupObservers()
        setupView(savedInstanceState)
        viewModel.onCreate()
    }

    private fun buildActivityComponent() =
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as CatchflicksApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()

    // any helper work like showing toast can be done here.
    protected open fun setupObservers() {

    }

    open fun goBack() = onBackPressed()

    // if there is any fragment inside the activity then it will pop first and
    // then the activity will be destroyed.
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStackImmediate()
        else super.onBackPressed()
    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    protected abstract fun setupView(savedInstanceState: Bundle?)

}