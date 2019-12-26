package com.akshay.catchflicks.ui.popular

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.akshay.catchflicks.R
import com.akshay.catchflicks.di.component.FragmentComponent
import com.akshay.catchflicks.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_popular.*

/**
 * Created by akshaynandwana on
 * 26, December, 2019
 **/
class PopularFragment : BaseFragment<PopularViewModel>() {

    companion object {

        const val TAG = "PopularFragment"

        fun newInstance(): PopularFragment {
            val args = Bundle()
            val fragment = PopularFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_popular

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.loading.observe(this, Observer {
            pbPop.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

}