package com.umc.save.Locker.Picture

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.save.Locker.data.Picture
import com.umc.save.R
import com.umc.save.databinding.ItemPictureBinding

class PictureRecordRVAdapter(private val pictureList : ArrayList<Picture>) : RecyclerView.Adapter<PictureRecordRVAdapter.ViewHolder>() {
    interface MyItemClickListener {
        fun onItemClick(picture: Picture)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener : MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPictureBinding = ItemPictureBinding.inflate(LayoutInflater.from(viewGroup.context),
            viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(pictureList[position])
        holder.itemView.findViewById<ImageView>(R.id.picture_plus).setOnClickListener{ mItemClickListener.onItemClick(pictureList[position]) }
    }

    override fun getItemCount(): Int = pictureList.size

    inner class ViewHolder(val binding: ItemPictureBinding) : RecyclerView.ViewHolder(binding.root) {

        private val image : ImageView = itemView.findViewById<ImageView>(R.id.picture_iv)

        fun bind(picture: Picture) {
            Glide.with(itemView)
                .load(picture.location)
                .into(image)

        }
    }

}

