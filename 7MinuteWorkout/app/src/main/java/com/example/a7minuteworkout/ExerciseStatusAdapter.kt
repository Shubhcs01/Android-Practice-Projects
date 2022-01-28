package com.example.a7minuteworkout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_exercise_status.view.*

class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>, val context: Context) :RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvRvItem = view.tvRvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_exercise_status,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: ExerciseModel = items[position]
        holder.tvRvItem.text = item.getId().toString()

        if(item.getIsSelected()){
            holder.tvRvItem.background = ContextCompat.getDrawable(context,
                R.drawable.item_circular_thin_color_accent_border)
            holder.tvRvItem.setTextColor(Color.parseColor("#212121"))
        } else if(item.getIsCompleted()){
            holder.tvRvItem.background = ContextCompat.getDrawable(context,
                R.drawable.item_circular_color_accent_background)
            holder.tvRvItem.setTextColor(Color.parseColor("#FFFFFF"))
        } else {
            holder.tvRvItem.background = ContextCompat.getDrawable(context,
                R.drawable.item_circular_color_grey_background)
            holder.tvRvItem.setTextColor(Color.parseColor("#212121"))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}