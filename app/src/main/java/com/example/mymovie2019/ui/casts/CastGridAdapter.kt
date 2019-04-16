package com.example.mymovie2019.ui.casts;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie2019.R
import com.example.mymovie2019.data.local.model.CastItem
import com.example.mymovie2019.data.local.model.ItemType
import com.example.mymovie2019.utils.AppKey.Companion.BASE_URL_IMAGE_PATH
import com.example.mymovie2019.utils.loadImageByUrl
import de.hdodenhof.circleimageview.CircleImageView


class CastGridAdapter(private val onItemClickListener: OnClickCastItemListener) :
        ListAdapter<CastItem, RecyclerView.ViewHolder>(CastDiffUtilCallBack()) {

    interface OnClickCastItemListener {
        fun onCastItemClick(view: View?, position: Int)
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position).itemType) {
        ItemType.Normal -> R.layout.item_cast_grid
        ItemType.ItemLoading -> R.layout.item_grid_load_more
        else -> R.layout.item_cast_grid
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.item_grid_load_more -> LoadingViewHolder(inflater.inflate(R.layout.item_grid_load_more, parent, false))
            R.layout.item_cast_grid -> CastViewHolder(inflater.inflate(R.layout.item_cast_grid, parent, false), onItemClickListener)
            else -> CastViewHolder(inflater.inflate(R.layout.item_cast_grid, parent, false), onItemClickListener)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CastViewHolder) {
            holder.bindData(getItem(position))
        }
    }

    class CastViewHolder(private val containerView: View, private val onItemClickListener: OnClickCastItemListener) :
            RecyclerView.ViewHolder(containerView), View.OnClickListener {

        var castImageView: CircleImageView = itemView.findViewById(R.id.image_item_cast_grid)
        var textCastName: TextView = itemView.findViewById(R.id.text_cast_name)

        init {
            containerView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemClickListener.onCastItemClick(p0, adapterPosition)
        }

        fun bindData(item: CastItem) {
            textCastName.text = item.name
            val itemImagePath = item.imagePath
            if (!itemImagePath.isNullOrEmpty()) {
                val finalImagePath = "${BASE_URL_IMAGE_PATH}$itemImagePath"
                castImageView.loadImageByUrl(finalImagePath)
            }

        }
    }

    class LoadingViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView)

    class CastDiffUtilCallBack : DiffUtil.ItemCallback<CastItem>() {

        override fun areItemsTheSame(oldItem: CastItem, newItem: CastItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CastItem, newItem: CastItem): Boolean {
            return oldItem == newItem
        }

    }


}