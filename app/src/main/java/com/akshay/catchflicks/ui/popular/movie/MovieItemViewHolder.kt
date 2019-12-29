package com.akshay.catchflicks.ui.popular.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.akshay.catchflicks.R
import com.akshay.catchflicks.data.model.Movie
import com.akshay.catchflicks.di.component.ViewHolderComponent
import com.akshay.catchflicks.ui.base.BaseItemViewHolder
import com.akshay.catchflicks.ui.popular.detail.PopularDetailActivity


/**
 * Created by akshaynandwana on
 * 27, December, 2019
 **/
class MovieItemViewHolder(
    parentViewGroup: ViewGroup
) : BaseItemViewHolder<Movie, MovieItemViewModel>(
    (DataBindingUtil.inflate<ViewDataBinding>(
        LayoutInflater.from(parentViewGroup.context),
        R.layout.item_popular, parentViewGroup, false
    ))
) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupView(view: View) {

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.launchDetail.observe(this, Observer {
            it.let {
                val intent = Intent(itemView.context, PopularDetailActivity::class.java)
                intent.putExtra("movie", it)
                itemView.context.startActivity(intent)
            }
        })
    }

}