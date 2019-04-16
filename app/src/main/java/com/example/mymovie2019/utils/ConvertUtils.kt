package com.example.mymovie2019.utils

import android.content.Context
import timber.log.Timber
import java.text.NumberFormat

class ConvertUtils {

    companion object {


        fun convertToPx(dp: Int, context: Context): Int {
            // Get the screen's density scale
            val scale = context.resources.displayMetrics.density;
            // Convert the dps to pixels, based on density scale
            return (dp * scale + 0.5f).toInt();
        }

        fun convertToHourAndMinute(totalMinutes: Int?): String {
            if (totalMinutes == null) {
                return ""
            }
            val hour = totalMinutes / 60
            val minute = totalMinutes % 60
            if (minute != 0) {
                return "${hour}h${minute}m"
            }
            return "$hour"
        }

        fun formatMoney(money: Int?): String {
            return try {
                val formatCurrency =  NumberFormat.getCurrencyInstance()
                formatCurrency.format(money)
            } catch (exception: Exception) {
                Timber.e(exception)
                "0"
            }
        }
    }
}