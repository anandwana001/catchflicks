package com.akshay.catchflicks.ui.popular.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.akshay.catchflicks.R
import com.akshay.catchflicks.data.model.Movie
import com.akshay.catchflicks.di.component.ViewHolderComponent
import com.akshay.catchflicks.ui.base.BaseItemViewHolder

/**
 * Created by akshaynandwana on
 * 27, December, 2019
 **/
class SearchItemViewHolder(
    parentViewGroup: ViewGroup
) : BaseItemViewHolder<Movie, SearchItemViewModel>(
    (DataBindingUtil.inflate<ViewDataBinding>(
        LayoutInflater.from(parentViewGroup.context),
        R.layout.item_search, parentViewGroup, false
    ))
) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupView(view: View) {

    }

}