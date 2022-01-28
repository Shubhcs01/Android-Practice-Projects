package com.example.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var todoList = mutableListOf(
                Todo("Follow AndroidDev",false),
                Todo("Learn RecyclerView",true),
                Todo("Feed my cat",false),
                Todo("Call friend",false),
                Todo("Be a manager",false),
                Todo("Follow AndroidDev",false),
        )

        val adapter = TodoAdapter(todoList)
        rvTodos.adapter = adapter
        rvTodos.layoutManager = LinearLayoutManager(this)

        btnAddTodo.setOnClickListener {
            val title = etTodo.text.toString()
            todoList.add(Todo(title,false))
            adapter.notifyItemInserted(todoList.size-1)
        }

    }
}