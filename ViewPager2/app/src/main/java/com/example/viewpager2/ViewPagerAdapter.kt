package com.example.viewpager2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view_pager.view.*

class ViewPagerAdapter(val images:List<Int>): RecyclerView.Adapter<ViewPagerAdapter.ViewPageViewHolder>() {

    inner class ViewPageViewHolder(itemview: View): RecyclerView.ViewHolder(itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPageViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager,parent,false)
        return ViewPageViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewPageViewHolder, position: Int) {
        val currImages = images[position]
        holder.itemView.ivImages.setImageResource(currImages)
    }

    override fun getItemCount(): Int {
       return images.size
    }
}