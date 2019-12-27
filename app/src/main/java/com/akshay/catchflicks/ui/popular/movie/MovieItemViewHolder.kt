package com.akshay.catchflicks.ui.popular.movie

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.akshay.catchflicks.R
import com.akshay.catchflicks.data.model.Movie
import com.akshay.catchflicks.di.component.ViewHolderComponent
import com.akshay.catchflicks.ui.base.BaseItemViewHolder
import com.akshay.catchflicks.utils.common.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_popular.view.*


/**
 * Created by akshaynandwana on
 * 27, December, 2019
 **/
class MovieItemViewHolder(
    parentViewGroup: ViewGroup
) : BaseItemViewHolder<Movie, MovieItemViewModel>(R.layout.item_popular, parentViewGroup) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupView(view: View) {
        //bookmark click
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.title.observe(this, Observer {
            itemView.tvTitle.text = it
        })

        viewModel.overview.observe(this, Observer {
            itemView.tvOverview.text = it
        })

        viewModel.posterPath.observe(this, Observer {
            it?.run {
                val imageUrl = Constants.IMAGE_URL_PREFIX + this

                val options = RequestOptions()
                options.centerInside()

                Glide.with(itemView.ivPoster.context)
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(options)
                    .into(itemView.ivPoster)
            }
        })

        viewModel.voteAverage.observe(this, Observer {
            itemView.tvVoteAverage.text = it.toString()
        })
    }

}