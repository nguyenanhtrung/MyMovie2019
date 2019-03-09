package com.example.mymovie2019.utils

import android.content.Context

class ConvertUtils {

    companion object {

        fun convertToPx(dp: Int, context: Context): Int {
            // Get the screen's density scale
            val scale = context.resources.displayMetrics.density;
            // Convert the dps to pixels, based on density scale
            return (dp * scale + 0.5f).toInt();
        }
    }
}