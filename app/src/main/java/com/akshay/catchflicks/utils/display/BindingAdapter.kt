package com.akshay.catchflicks.utils.display

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.akshay.catchflicks.utils.common.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions


/**
 * Created by akshaynandwana on
 * 29, December, 2019
 **/
object BindingAdapter {

    @BindingAdapter("posterImage")
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String) {
        imageUrl.let {
            val url = Constants.IMAGE_URL_PREFIX + it

            val options = RequestOptions()
            options.centerInside()

            Glide.with(view.getContext())
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options)
                .into(view)
        }
    }

    @BindingAdapter("voteAverage")
    @JvmStatic
    fun setFloatText(view: TextView, value: Float?) {
        value?.let {
            view.text = value.toString()
        }
    }
}