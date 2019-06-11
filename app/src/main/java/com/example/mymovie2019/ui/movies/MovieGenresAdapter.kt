package com.example.mymovie2019.ui.movies;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie2019.R
import com.example.mymovie2019.data.local.database.entity.GenreEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie_category.*


class MovieGenresAdapter(private val onItemClickListener: OnClickGenreItemListener) : ListAdapter<GenreEntity,MovieGenresAdapter.GenresViewHolder>(GenreDiffUtilCallBack()) {

    interface OnClickGenreItemListener {
        fun onGenreItemClick(view: View?, position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GenresViewHolder(inflater.inflate(R.layout.item_movie_category, parent, false), onItemClickListener)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }


    class GenresViewHolder(override val containerView: View, private val onItemClickListener: OnClickGenreItemListener) : RecyclerView.ViewHolder(containerView), View.OnClickListener, LayoutContainer {

        init {
            containerView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemClickListener.onGenreItemClick(p0, adapterPosition)
        }

        fun bindData(item: GenreEntity) {
            text_category_name.text = item.name
        }
    }

    class GenreDiffUtilCallBack : DiffUtil.ItemCallback<GenreEntity>() {

        override fun areItemsTheSame(oldItem: GenreEntity, newItem: GenreEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GenreEntity, newItem: GenreEntity): Boolean {
            return oldItem == newItem
        }

    }


}