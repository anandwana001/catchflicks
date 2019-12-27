package com.akshay.catchflicks.ui.popular.movie

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.akshay.catchflicks.data.model.Movie
import com.akshay.catchflicks.ui.base.BaseAdapter

/**
 * Created by akshaynandwana on
 * 27, December, 2019
 **/
class MovieUpcomingAdapter(
    parentLifecycle: Lifecycle,
    movie: ArrayList<Movie>
) : BaseAdapter<Movie, MovieUpcomingItemViewHolder>(parentLifecycle, movie) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieUpcomingItemViewHolder =
        MovieUpcomingItemViewHolder(parent)

}