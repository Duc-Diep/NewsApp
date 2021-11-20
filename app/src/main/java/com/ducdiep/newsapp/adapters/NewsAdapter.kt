package com.ducdiep.newsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ducdiep.newsapp.R
import com.ducdiep.newsapp.models.Article
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(var context: Context,var listArticle:List<Article>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    var onClick:((Article)->Unit)? = null
    fun setonClickItem(callBack:(Article)->Unit){
        onClick = callBack
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.news_item,parent,false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        var article = listArticle[position]
        Glide.with(context).load(article.urlToImage).into(holder.imgAvatar)
        holder.tvTitle.text = article.title
        holder.tvDescription.text = article.description
        holder.tvDescription.isSelected =true
        holder.tvSource.text = article.source.name
        holder.tvDate.text = formatDate(article.publishedAt)
        holder.itemView.setOnClickListener{
            onClick?.invoke(article)
        }
    }

    override fun getItemCount(): Int {
        return listArticle.size
    }
    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgAvatar = itemView.findViewById<ImageView>(R.id.img_avatar)
        var tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        var tvDescription = itemView.findViewById<TextView>(R.id.tv_description)
        var tvSource = itemView.findViewById<TextView>(R.id.tv_source_name)
        var tvDate = itemView.findViewById<TextView>(R.id.tv_date)
    }
    fun formatDate(str:String):String{
        var formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        var date = formatter.parse(str)
        var formatDate = SimpleDateFormat("E MMM dd HH:mm",Locale("en"))
        return formatDate.format(date)
    }
}