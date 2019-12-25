package com.akshay.catchflicks.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.akshay.catchflicks.R
import com.akshay.catchflicks.di.component.ActivityComponent
import com.akshay.catchflicks.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.genreList.observe(this, Observer {
            tvGenreList.text = it.toString()
        })
    }

}
