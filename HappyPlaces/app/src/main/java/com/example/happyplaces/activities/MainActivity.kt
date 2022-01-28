package com.example.happyplaces.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.happyplaces.R
import com.example.happyplaces.adapter.HappyPlaceAdapter
import com.example.happyplaces.database.DatabaseHandler
import com.example.happyplaces.models.HappyPlaceModel
import com.example.happyplaces.utils.SwipeToDeleteCallback
import com.example.happyplaces.utils.SwipeToEditCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_btnAdd.setOnClickListener {
            val intent = Intent(this, AddHappyPlaceActivity::class.java)
            startActivityForResult(intent, ADD_PLACE_TO_HOME)
        }
        getHappyPlacesListFromLocalDB()
    }

    private fun getHappyPlacesListFromLocalDB(){
        val dbHandler = DatabaseHandler(this)
        val getHappyPlaceList: ArrayList<HappyPlaceModel> = dbHandler.getAllPlacesList()

        if(getHappyPlaceList.size > 0){

            rv_happy_places_list.visibility = View.VISIBLE
            tv_no_records_available.visibility = View.GONE

            // Attach the adapter and Set layout manager
            rv_happy_places_list.layoutManager = LinearLayoutManager(this)
            rv_happy_places_list.setHasFixedSize(true)
            val placeAdapter =  HappyPlaceAdapter(this, getHappyPlaceList)
            rv_happy_places_list.adapter = placeAdapter

            //-> Onclick listener setUp
           placeAdapter.setOnClickListener(object : HappyPlaceAdapter.OnClickListener{
                   override fun onClick(position: Int, model: HappyPlaceModel) {
                       val intent = Intent(this@MainActivity, HappyPlaceDetailActivity::class.java)
                       intent.putExtra(EXTRA_PLACE_DETAILS, model)
                       startActivity(intent)
                   }
           })

            val editSwipeHandler = object : SwipeToEditCallback(this) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val adapter = rv_happy_places_list.adapter as HappyPlaceAdapter
                    adapter.notifyEditItem(this@MainActivity, viewHolder.adapterPosition, ADD_PLACE_TO_HOME)
                }

            }
            val editItemTouchHelper = ItemTouchHelper(editSwipeHandler)
            editItemTouchHelper.attachToRecyclerView(rv_happy_places_list)

            val deleteSwipeHandler = object : SwipeToDeleteCallback(this) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val adapter = rv_happy_places_list.adapter as HappyPlaceAdapter
                    adapter.removeAt(viewHolder.absoluteAdapterPosition)
                    getHappyPlacesListFromLocalDB()
                }
            }
            val deleteItemTouchHelper = ItemTouchHelper(deleteSwipeHandler)
            deleteItemTouchHelper.attachToRecyclerView(rv_happy_places_list)


        } else {
            rv_happy_places_list.visibility = View.GONE
            tv_no_records_available.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADD_PLACE_TO_HOME) {
            if(resultCode == Activity.RESULT_OK){
                getHappyPlacesListFromLocalDB()
            } else {
                Log.e("Activity", "Cancelled or pressed back")
            }
        }
    }

    companion object{
          var ADD_PLACE_TO_HOME = 1
          var EXTRA_PLACE_DETAILS = "extra_place_details"
    }
}