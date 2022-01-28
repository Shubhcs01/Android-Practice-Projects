package com.example.stylishapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spMonths.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            //--> Anonymous class
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, i: Int, id: Long) {
                Toast.makeText(this@MainActivity,"You selected ${adapterView?.getItemAtPosition(i).toString()}", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        val addContactDialogBox = AlertDialog.Builder(this)
            .setTitle("Add Contact")
            .setMessage("Do you Want to add Mr. Poop on your contact list?")
            .setIcon(R.drawable.ic_add_contact)
            .setPositiveButton("yes"){_,_ ->
                Toast.makeText(this,"You added Mr. poop to your contact list.",Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No"){_,_ ->
                Toast.makeText(this,"You didn't added Mr. poop to your contact list.",Toast.LENGTH_SHORT).show()
            }.create()
        btnDialog1.setOnClickListener {
            addContactDialogBox.show()
        }

        val options = arrayOf("First item", "Second item", "Third item")
        val singleChoiceDialogBox = AlertDialog.Builder(this)
            .setTitle("Choose one of these options:")
            .setSingleChoiceItems(options,0){DialogInterface, i ->
                Toast.makeText(this,"you clicked on ${options[i]}",Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Accept"){_,_ ->
                Toast.makeText(this,"You accept the singleChoiceDialog.",Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Decline"){_,_ ->
                Toast.makeText(this,"You didn't accept the singleChoiceDialog.",Toast.LENGTH_SHORT).show()
            }.create()
        btnDialog2.setOnClickListener {
            singleChoiceDialogBox.show()
        }


        val multiChoiceDialogBox = AlertDialog.Builder(this)
            .setTitle("Choose one of these options:")
            .setMultiChoiceItems(options, booleanArrayOf(false, false, false)){_, i, isChecked ->
               if(isChecked){
                   Toast.makeText(this,"you checked ${options[i]}",Toast.LENGTH_SHORT).show()
               } else {
                   Toast.makeText(this,"you unchecked ${options[i]}",Toast.LENGTH_SHORT).show()
               }
            }
            .setPositiveButton("Accept"){_,_ ->
                Toast.makeText(this,"You accept the multiChoiceDialog.",Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Decline"){_,_ ->
                Toast.makeText(this,"You declined the multiChoiceDialog.",Toast.LENGTH_SHORT).show()
            }.create()
        btnDialog3.setOnClickListener {
            multiChoiceDialogBox.show()
        }
    }

    //--> Only Display Toolbar Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.miAddContact -> Toast.makeText(this, "You clicked on Add Contact", Toast.LENGTH_SHORT).show()
            R.id.miFavourites -> Toast.makeText(this, "You clicked on Favorites", Toast.LENGTH_SHORT).show()
            R.id.miSettings -> Toast.makeText(this, "You clicked on Settings", Toast.LENGTH_SHORT).show()
            R.id.miFeedback -> Toast.makeText(this, "You clicked on Feedback", Toast.LENGTH_SHORT).show()
            R.id.miClose -> finish()
        }
        return true
    }

}