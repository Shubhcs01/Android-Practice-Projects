 package com.example.apis

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


 class MainActivity : AppCompatActivity() {

     lateinit var adapter: NewsAdapter
     private var articles = mutableListOf<Article>()
     var pageNum = 1
     var totalResult = -1
     var category = ""

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

         adapter = NewsAdapter(this@MainActivity, articles)
         rvNewsList.adapter = adapter

//        rvNewsList.layoutManager = LinearLayoutManager(this@MainActivity)

         val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
         layoutManager.setPagerMode(true);
         layoutManager.setPagerFlingVelocity(3000);
         rvNewsList.layoutManager = layoutManager

         layoutManager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener {
             override fun onItemChanged(position: Int) {
//                 container.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))
                 Log.d("MainActivity", "First visible item - ${layoutManager.getFirstVisibleItemPosition()}")
                 Log.d("MainActivity", "Total count - ${layoutManager.itemCount}")
                 if (totalResult > layoutManager.itemCount && layoutManager.getFirstVisibleItemPosition() >= layoutManager.itemCount - 5) {
                     // next page
                     pageNum++
                     getNews()
                 }
             }
         })


         spCategoryList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
             //--> Anonymous class
             override fun onItemSelected(
                 adapterView: AdapterView<*>?,
                 view: View?,
                 i: Int,
                 id: Long
             ) {
                 category = adapterView?.getItemAtPosition(i).toString()
                 if(articles.isNotEmpty()) {
                     pageNum = 1
                     totalResult = -1
                     articles.clear();
                     rvNewsList.removeAllViews();
                 }
                 getNews()
             }

                 override fun onNothingSelected(p0: AdapterView<*>?) {
                 }
             }

             getNews()
         }

         private fun getNews() {
             Log.d("MainActivity", "Request sent for $pageNum")
             val news = NewsService.newsInstance.getHeadLines("in", category, pageNum)
             //--> Passing callbacks
             news.enqueue(object : Callback<News> {
                 override fun onResponse(call: Call<News>, response: Response<News>) {
                     val news = response.body()
                     if (news != null) {
                         Log.d("Shubham", news.toString())
                         totalResult = news.totalResults
                         //--> Adding items to list article.
                         articles.addAll(news.articles)
                         adapter.notifyDataSetChanged()
                     }
                 }

                 override fun onFailure(call: Call<News>, t: Throwable) {
                     Log.d("Shubham", "Error in fetching news")
                 }
             })
         }
 }