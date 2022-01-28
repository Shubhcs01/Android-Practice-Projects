package com.example.apis

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view.view.*

class NewsAdapter(val context: Context ,val articles: List<Article>): RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view,parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.itemView.apply {
            tvTitle.text = articles[position].title
            tvDescription.text = articles[position].description

            /** We can not bind Image directly here. we need Glide for this. */
            Glide.with(context).load(articles[position].urlToImage).into(ivNewsImg)

            //--> Bind ClickHandler
            setOnClickListener {
                Toast.makeText(context, articles[position].title, Toast.LENGTH_SHORT).show()

                //--> Another Activity
                Intent(context, DetailActivity::class.java).also {
                    it.putExtra("URL", articles[position].url)
                    context.startActivity(it)
                }
            }

//            var color = "#cccccc"
//            if(position % 2 == 0){
//               color = "#EEEEEE"
//           }
//            container.setBackgroundColor(Color.parseColor(color))
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

}