package com.akshay.catchflicks.ui.popular

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.akshay.catchflicks.R
import com.akshay.catchflicks.di.component.FragmentComponent
import com.akshay.catchflicks.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_upcoming.*

/**
 * Created by akshaynandwana on
 * 26, December, 2019
 **/
class UpcomingFragment : BaseFragment<PopularViewModel>() {

    companion object {

        const val TAG = "UpcomingFragment"

        fun newInstance(): UpcomingFragment {
            val args = Bundle()
            val fragment = UpcomingFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_upcoming

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.loading.observe(this, Observer {
            pbUpcoming.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

}