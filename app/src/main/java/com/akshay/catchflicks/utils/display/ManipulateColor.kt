package com.akshay.catchflicks.utils.display

import android.graphics.Color

/**
 * Created by akshaynandwana on
 * 29, December, 2019
 **/
object ManipulateColor {

    @JvmStatic
    fun manipulateColor(color: Int, factor: Float): Int {
        val a = Color.alpha(color)
        val r = Math.round(Color.red(color) * factor)
        val g = Math.round(Color.green(color) * factor)
        val b = Math.round(Color.blue(color) * factor)
        return Color.argb(
            a,
            Math.min(r, 255),
            Math.min(g, 255),
            Math.min(b, 255)
        )
    }
}