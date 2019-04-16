package com.example.mymovie2019.ui.listmovie;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie2019.R
import com.example.mymovie2019.data.local.model.MovieTransfer
import com.example.mymovie2019.utils.AppKey
import com.example.mymovie2019.utils.loadImageByUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie_vertical.*


class ListMovieVerticalAdapter(private val onItemClickListener: OnClickMoveItemListener) : ListAdapter<MovieTransfer, ListMovieVerticalAdapter.ListMovieViewHolder>(ListMovieDiffUtilCallback()) {

    interface OnClickMoveItemListener {
        fun onItemClick(view: View?, position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListMovieViewHolder(inflater.inflate(R.layout.item_movie_vertical, parent, false), onItemClickListener)
    }

    override fun onBindViewHolder(holder: ListMovieViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class ListMovieViewHolder(override val containerView: View, private val onItemClickListener: OnClickMoveItemListener) : RecyclerView.ViewHolder(containerView), View.OnClickListener, LayoutContainer {

        init {
            containerView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemClickListener.onItemClick(p0, adapterPosition)
        }

        fun bindData(item: MovieTransfer) {
            with(item) {
                text_movie_name_vertical.text = name
                text_release_date_vertical.text = releaseDate
                rating_bar_movie_vertical.rating = (rating / 2).toFloat()
                if (imagePath != null) {
                    image_movie_vertical.loadImageByUrl("${AppKey.BASE_URL_IMAGE_PATH}$imagePath")
                }
            }
        }
    }

    class ListMovieDiffUtilCallback : DiffUtil.ItemCallback<MovieTransfer>() {

        override fun areItemsTheSame(oldItem: MovieTransfer, newItem: MovieTransfer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieTransfer, newItem: MovieTransfer): Boolean {
            return oldItem == newItem
        }

    }


}