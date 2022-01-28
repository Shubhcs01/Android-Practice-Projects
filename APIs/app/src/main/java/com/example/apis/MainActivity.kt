package com.example.apis

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()
    }

    private fun getNews(){
        val news = NewsService.newsInstance.getHeadLines("in", 1)
        //--> Passing callbacks
        news.enqueue(object :Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
               val news = response.body()
                if (news!=null){
                    Log.d("Shubham",news.toString())

                    //--> Bind RecyclerView here.
                    rvNewsList.adapter = NewsAdapter(this@MainActivity, news.articles)
                    rvNewsList.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Shubham","Error in fetching news")
            }
        })
    }
}