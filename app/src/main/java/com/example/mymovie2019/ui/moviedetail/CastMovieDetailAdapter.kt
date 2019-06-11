package com.example.mymovie2019.ui.moviedetail;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie2019.R
import com.example.mymovie2019.data.remote.response.Cast
import com.example.mymovie2019.utils.AppKey
import com.example.mymovie2019.utils.loadImageByUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_cast_vertical.*


class CastMovieDetailAdapter(
    private val onItemClickListener: OnClickCastItemListener
) : ListAdapter<Cast, CastMovieDetailAdapter.CastMovieDetailViewHolder>(CastMovieDetailDiffUtilCallback()) {

    interface OnClickCastItemListener {
        fun onCastItemClick(view: View?, position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastMovieDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CastMovieDetailViewHolder(
                inflater.inflate(R.layout.item_cast_vertical, parent, false),
                onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: CastMovieDetailViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    fun getItemByPosition(position: Int): Cast {
        return getItem(position)
    }

    class CastMovieDetailViewHolder(
        override val containerView: View,
        private val onItemClickListener: OnClickCastItemListener
    ) : RecyclerView.ViewHolder(containerView), View.OnClickListener, LayoutContainer {

        init {
            containerView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemClickListener.onCastItemClick(p0, adapterPosition)
        }

        fun bindData(item: Cast) {
            text_character_name.text = item.character
            text_cast_name_horizontal.text = item.name
            if (!item.imageUrl.isNullOrEmpty()) {
                image_cast.loadImageByUrl("${AppKey.URL_MOVIE_ITEM_IMAGE_PATH}${item.imageUrl}")
            }

        }
    }

    class CastMovieDetailDiffUtilCallback : DiffUtil.ItemCallback<Cast>() {

        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }

    }


}