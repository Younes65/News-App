package com.example.younes_rcc010

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomAdaptar (val activity : Activity , val article : ArrayList<Article>?)
    :RecyclerView.Adapter<CustomAdaptar.NewsVH>(){
    class NewsVH(view:View):RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.txt)
        val image : ImageView = view.findViewById(R.id.iv)
        val card :CardView = view.findViewById(R.id.card)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdaptar.NewsVH {
       val view = activity
           .layoutInflater
           .inflate(R.layout.new_list_item,parent,false)

        return NewsVH(view)
    }

    override fun onBindViewHolder(holder: CustomAdaptar.NewsVH, position: Int) {
        holder.title.text = article?.get(position)?.title
        Glide
            .with(activity)
            .load(article?.get(position)?.urlToImage)
            .into(holder.image)
        holder.card.setOnClickListener {
            val link = article?.get(position)?.url
            val uri = Uri.parse(link)
            val i = Intent(Intent.ACTION_VIEW,uri)
            activity.startActivity(i)

        }
    }

    override fun getItemCount() = article?.size ?: 0
}