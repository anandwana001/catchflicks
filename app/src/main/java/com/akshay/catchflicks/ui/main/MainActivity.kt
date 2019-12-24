package com.akshay.catchflicks.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.akshay.catchflicks.CatchflicksApplication
import com.akshay.catchflicks.R
import com.akshay.catchflicks.di.component.DaggerActivityComponent
import com.akshay.catchflicks.di.module.ActivityModule
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        buildActivityComponent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.genreList.observe(this, Observer {
            tvGenreList.text = it.toString()
        })

        viewModel.getGenreList()
    }

    private fun buildActivityComponent() =
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as CatchflicksApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}
