package edu.temple.viewpagerinclasssctivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    //
    private var numberOfPages = 0

    private val newButton: Button by lazy{
        findViewById(R.id.button)
    }

    // viewPager is only initialized when we first try to access it
    val viewPager : ViewPager2 by lazy{
        findViewById(R.id.viewPager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newButton.setOnClickListener{
            numberOfPages++
            // let fragment state adapter know that data has changed
            viewPager.adapter?.notifyItemInserted(numberOfPages-1)
            viewPager.setCurrentItem(numberOfPages-1, false)

        }


        // 'this' refers to is what the view pager is in, in this case the Activity is what it's in
        viewPager.adapter = object: FragmentStateAdapter(this){
            override fun getItemCount() = numberOfPages

            // position 0 -> 10-1
            // each time you swipe across a view pager, the old fragment is destroyed and a clone is put in its place
            override fun createFragment(position: Int): Fragment = TextFragment.newInstance((position+1).toString())

        }
    }
}