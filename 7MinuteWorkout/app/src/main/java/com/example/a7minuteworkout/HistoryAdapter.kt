package com.example.a7minuteworkout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_history_layout.view.*

class HistoryAdapter(val context: Context, val items: ArrayList<String>):
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val llHistoryMainItem = view.ll_history_item_main
        val tvItem = view.tvItem
        val tvPosition = view.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val date: String = items[position]
        holder.tvItem.text = date
        holder.tvPosition.text = (position+1).toString()

        if(position%2==0){
            holder.llHistoryMainItem.setBackgroundColor(
                Color.parseColor("#EBEBEB")
            )
        } else {
            holder.llHistoryMainItem.setBackgroundColor(
                Color.parseColor("#FFFFFF")
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}