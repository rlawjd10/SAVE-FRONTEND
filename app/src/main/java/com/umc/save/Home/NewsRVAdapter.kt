package com.umc.save.Home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.save.MainActivity
import com.umc.save.databinding.ItemNewsBinding
import android.content.Context as Context1

class NewsRVAdapter: RecyclerView.Adapter<NewsRVAdapter.ViewHolder>() {

    var itemList = mutableListOf<NewsData>()

    inner class ViewHolder(private val viewBinding: ItemNewsBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(newsData: NewsData) {
            viewBinding.homeNewsTitleTv.text = newsData.text
            viewBinding.homeNewsDateTv.text = newsData.date
            //viewBinding.homeNewsIv.setImageResource(newsData.image)
            Glide.with(itemView.context)
                .load(newsData.image)
                .into(viewBinding.homeNewsIv)

            itemView.setOnClickListener {
                var url = Intent(Intent.ACTION_VIEW, Uri.parse(newsData.url))
                /*val openUrl = Intent(android.content.Intent.ACTION_VIEW)
                openUrl.data = Uri.parse(newsData.url)*/

                //val intent = Intent(itemView?.context, MainActivity::class.java)
                ContextCompat.startActivity(itemView.context, url, null)

                /*val context: Context? = null
                context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(newsData.url)))*/

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}