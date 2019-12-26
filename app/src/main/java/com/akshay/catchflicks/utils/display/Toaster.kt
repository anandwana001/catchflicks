package com.akshay.catchflicks.utils.display

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.akshay.catchflicks.R


/**
 * Created by akshaynandwana on
 * 23, December, 2019
 **/

object Toaster {

    fun show(context: Context, text: CharSequence) {
        val toast = android.widget.Toast.makeText(context, text, android.widget.Toast.LENGTH_SHORT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            toast.view.background.setColorFilter(
                BlendModeColorFilter(
                    R.color.white,
                    BlendMode.SRC_ATOP
                )
            )
        } else {
            toast.view.background.setColorFilter(
                ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_IN
            )
        }
        val textView = toast.view.findViewById(android.R.id.message) as TextView
        textView.setTextColor(ContextCompat.getColor(context, R.color.black))
        toast.show()
    }

}