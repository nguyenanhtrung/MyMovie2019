package com.example.mymovie2019.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager

class MyViewPager : ViewPager {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var customHeightMeasureSpec = heightMeasureSpec
        try {
            val currentPagePosition = 0
            val child = getChildAt(currentPagePosition)
            if (child != null) {
                child.measure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
                val h = child.measuredHeight
                customHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY)
            }
        }catch (exception: Exception) {
            exception.printStackTrace()
        }

        super.onMeasure(widthMeasureSpec, customHeightMeasureSpec)
    }


}