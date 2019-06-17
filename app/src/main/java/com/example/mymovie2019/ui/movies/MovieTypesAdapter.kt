package com.example.mymovie2019.ui.movies;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie2019.R
import com.example.mymovie2019.data.local.model.MovieTransfer
import com.example.mymovie2019.data.local.model.MoviesVerticalItem
import com.example.mymovie2019.utils.EndlessScrollListener


class MovieTypesAdapter(private val onLoadMoreMovieItemListener: OnLoadMoreMovieItemListener,
                        private val onClickMovieItem: OnClickItemMovieHorizontalListener,
                        private val onClickMovieVerticalItemListener: OnClickMovieVerticalItemListener) : ListAdapter<MoviesVerticalItem, MovieTypesAdapter.MovieTypesViewHolder>(MoviesTypeItemDiffCallBack()) {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTypesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieTypesViewHolder(inflater.inflate(R.layout.item_vertical_recyclerview_movies, parent, false),
                viewPool,
                onLoadMoreMovieItemListener,
                onClickMovieItem,
                onClickMovieVerticalItemListener)
    }

    override fun onBindViewHolder(holder: MovieTypesViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }


    class MovieTypesViewHolder(containerView: View,
                               private val viewPool: RecyclerView.RecycledViewPool,
                               private val onLoadMoreMovieItemListener: OnLoadMoreMovieItemListener,
                               private val onClickMovieItem: OnClickItemMovieHorizontalListener,
                               private val onClickMovieVerticalItemListener: OnClickMovieVerticalItemListener) : RecyclerView.ViewHolder(containerView),
            MovieHorizontalAdapter.OnClickMovieItem, View.OnClickListener {


        private var textTitle: TextView
        private var recyclerViewMovies: RecyclerView
        private var textSeeAll: TextView

        init {
            with(containerView) {
                textSeeAll = findViewById(R.id.text_title_see_all)
                textTitle = findViewById(R.id.text_title_movie_type)
                recyclerViewMovies = findViewById(R.id.recycler_view_movies)
                val moviesLayoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                recyclerViewMovies.apply {
                    layoutManager = moviesLayoutManager
                    adapter = MovieHorizontalAdapter(this@MovieTypesViewHolder)
                    addOnScrollListener(object : EndlessScrollListener(layoutManager as LinearLayoutManager) {
                        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                            onLoadMoreMovieItemListener.onLoadMoreMovie(page,adapterPosition)
                        }

                    })
                    setRecycledViewPool(viewPool)
                }
                textSeeAll.setOnClickListener(this@MovieTypesViewHolder)
            }
        }

        fun bindData(item: MoviesVerticalItem) {
            textTitle.text = item.title
            val adapter = recyclerViewMovies.adapter as MovieHorizontalAdapter
            adapter.submitList(item.movieItems)

        }

        //OnItemClick Movies Horizontal
        override fun onItemClick(imageView: ImageView, textName: TextView, textDate: TextView, position: Int) {
            val movieHorizontalAdapter = recyclerViewMovies.adapter as MovieHorizontalAdapter
            val movieItem = movieHorizontalAdapter.getItemByPosition(position)
            val movieTransfer = MovieTransfer(movieItem)
            onClickMovieItem.onItemMovieLick(movieTransfer, imageView, textName, textDate)
        }

        //On Movie Type Item Click
        override fun onClick(v: View?) {
            onClickMovieVerticalItemListener.onClickTextSeeAll(v!!, adapterPosition)
        }
    }

    class MoviesTypeItemDiffCallBack : DiffUtil.ItemCallback<MoviesVerticalItem>() {

        override fun areItemsTheSame(oldItem: MoviesVerticalItem, newItem: MoviesVerticalItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviesVerticalItem, newItem: MoviesVerticalItem): Boolean {
            return oldItem == newItem
        }
    }

    interface OnLoadMoreMovieItemListener {
        fun onLoadMoreMovie(page: Int, listPosition: Int)
    }

    interface OnClickItemMovieHorizontalListener {
        fun onItemMovieLick(movieTransfer: MovieTransfer, imageView: ImageView, textName: TextView, textDate: TextView)
    }

    interface OnClickMovieVerticalItemListener {
        fun onClickTextSeeAll(view: View, position: Int)
    }

}