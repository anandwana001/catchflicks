package com.akshay.catchflicks.ui.popular.movie

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.akshay.catchflicks.R
import com.akshay.catchflicks.data.model.Genre
import com.akshay.catchflicks.di.component.ViewHolderComponent
import com.akshay.catchflicks.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.item_genre.view.*
import java.util.*

/**
 * Created by akshaynandwana on
 * 27, December, 2019
 **/
class GenreItemViewHolder(
    parentViewGroup: ViewGroup
) : BaseItemViewHolder<Genre, GenreItemViewModel>(
    (DataBindingUtil.inflate<ViewDataBinding>(
        LayoutInflater.from(parentViewGroup.context),
        R.layout.item_genre, parentViewGroup, false
    ))
) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupView(view: View) {
        val rnd = Random()
        val currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        itemView.cvGenre.setCardBackgroundColor(currentColor)
    }

}