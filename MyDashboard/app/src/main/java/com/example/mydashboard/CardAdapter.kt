package com.example.mydashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(): RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_items,parent,false)
        return CardViewHolder(view)
    }

    var i = 0
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.itemView.apply {
            i=position
        }
    }

    override fun getItemCount(): Int {

        return i
    }

}