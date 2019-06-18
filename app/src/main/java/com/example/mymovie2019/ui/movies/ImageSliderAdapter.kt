package com.example.mymovie2019.ui.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.mymovie2019.R
import com.example.mymovie2019.data.local.model.MovieItem
import com.example.mymovie2019.data.local.model.MovieTransfer
import com.example.mymovie2019.utils.AppKey
import com.example.mymovie2019.utils.loadImageByUrl

class ImageSliderAdapter(private val context: Context,
                         private val sliderImages: List<MovieItem>,
                         private val onClickMovieSliderItemListener: OnClickMovieSliderItemListener) : PagerAdapter() {

    interface OnClickMovieSliderItemListener {
        fun onClickMovieSliderItem(view: ImageView, movieTransfer: MovieTransfer)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.item_image_slider, container, false)
        val imageView = view.findViewById<ImageView>(R.id.image_view_slide)
        imageView.apply {
            setOnClickListener {
                val movieTransfer = MovieTransfer(sliderImages[position])
                onClickMovieSliderItemListener.onClickMovieSliderItem(this, movieTransfer)
            }
        }
        val sliderImageItem = sliderImages[position]
        imageView.loadImageByUrl("${AppKey.BASE_URL_SLIDER_IMAGE_PATH}${sliderImageItem.imageUrl}",500,500)
        container.addView(view,0)
        return view
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean = view === obj

    override fun getCount(): Int = sliderImages.size

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
       container.removeView(obj as View)
    }

}