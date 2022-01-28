package com.example.viewpager2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = listOf(
            R.drawable.kermita,
            R.drawable.kermitb,
            R.drawable.kermitc,
            R.drawable.kermitd,
            R.drawable.kermite,
            R.drawable.kermitf,
            R.drawable.kermitg,
            R.drawable.kermith,
            R.drawable.kermiti
        )

        //--> create adapter for viewPagerAdapter
        val adapt = ViewPagerAdapter(images)
        viewPager.adapter = adapt

        //--> For changing Orientation
//        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

        //--> automatic swipe
        viewPager.beginFakeDrag()
        viewPager.fakeDragBy(-10f)
        viewPager.endFakeDrag()
    }
}