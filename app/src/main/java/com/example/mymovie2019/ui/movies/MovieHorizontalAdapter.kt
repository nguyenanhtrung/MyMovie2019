package com.example.mymovie2019.ui.movies;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie2019.R
import com.example.mymovie2019.data.local.model.ItemType
import com.example.mymovie2019.data.local.model.MovieItem
import com.example.mymovie2019.utils.AppKey
import com.example.mymovie2019.utils.loadImageByUrl


class MovieHorizontalAdapter(
    private val onItemClickListener: OnClickMovieItem
) : ListAdapter<MovieItem, RecyclerView.ViewHolder>(MovieItemDiffCallBack()) {


    interface OnClickMovieItem {
        fun onItemClick(view: View?, position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        when (viewType) {
            R.layout.item_movie_horizontal -> return MovieHorizontalViewHolder(
                inflater.inflate(
                    R.layout.item_movie_horizontal,
                    parent,
                    false
                ), onItemClickListener
            )
            R.layout.item_list_loading -> return EmptyViewHolder(
                inflater.inflate(
                    R.layout.item_list_loading,
                    parent,
                    false
                ), onItemClickListener
            )

            R.layout.item_loading_more -> return ItemLoadingViewHolder(
                inflater.inflate(
                    R.layout.item_loading_more,
                    parent,
                    false
                )
            )
        }

        return MovieHorizontalViewHolder(
            inflater.inflate(R.layout.item_movie_horizontal, parent, false),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieHorizontalViewHolder) {
            holder.bindData(getItem(position))
        }
    }


    override fun getItemViewType(position: Int): Int = when (getItem(position).itemType) {
        ItemType.ListLoading -> R.layout.item_list_loading
        ItemType.Normal -> R.layout.item_movie_horizontal
        ItemType.ItemLoading -> R.layout.item_loading_more
    }

    class MovieHorizontalViewHolder(view: View, private val onItemClickListener: OnClickMovieItem) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        lateinit var imageMovie: ImageView
        lateinit var textMovieName: TextView
        lateinit var textReleaseDate: TextView
        lateinit var ratingMovie: RatingBar

        init {
            with(itemView) {
                imageMovie = findViewById(R.id.image_movie_horizontal)
                textMovieName = findViewById(R.id.text_movie_name)
                textReleaseDate = findViewById(R.id.text_release_date)
                ratingMovie = findViewById(R.id.rating_bar_movie_horizontal)
            }
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemClickListener.onItemClick(p0, adapterPosition)
        }

        fun bindData(item: MovieItem) {
            textMovieName.text = item.name
            textReleaseDate.text = item.releaseDate
            val imageUrl = "${AppKey.BASE_URL_IMAGE_PATH}${item.imageUrl}"
            imageMovie.loadImageByUrl(imageUrl)
            val movieRating = item.rating.toFloat()
            ratingMovie.rating = movieRating / 2

        }
    }


    class EmptyViewHolder(itemView: View, private val onItemClickListener: OnClickMovieItem) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        override fun onClick(p0: View?) {
            onItemClickListener.onItemClick(p0, adapterPosition)
        }

    }

    class ItemLoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    class MovieItemDiffCallBack : DiffUtil.ItemCallback<MovieItem>() {

        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem == newItem
        }

    }
}