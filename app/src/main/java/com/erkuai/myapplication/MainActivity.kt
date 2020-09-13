package com.erkuai.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.erkuai.myapplication.widget.ConsecutiveScrollerLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adapter_main.view.*
import kotlin.math.log

class MainActivity : AppCompatActivity(), ConsecutiveScrollerLayout.OnScrollChangeListener {


    var position = IntArray(2)
    var out_position = IntArray(2)
    var measure = true

    val leftFragment = LeftFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fragments = arrayListOf<Fragment>()

        fragments.add(leftFragment)
        fragments.add(RightFragment())
        viewpager.currentItem = 0
        viewpager.offscreenPageLimit = fragments.size

        viewpager.adapter = MyViewPagerAdapter(supportFragmentManager, fragments)

        out_layout.setOnVerticalScrollChangeListener(this)


    }

    fun getOutTitle(): LinearLayout {

        return out_title_layout
    }


    fun getLayout(): ConsecutiveScrollerLayout {
        return out_layout
    }


    class MyViewPagerAdapter(
        private var fm: FragmentManager,
        private var data: ArrayList<Fragment>
    ) : FragmentPagerAdapter(fm) {
        override fun getCount() = data.size

        override fun getItem(position: Int) = data[position]

    }


    class MyAdapter(private var data: ArrayList<String>) :
        RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder>() {

        class MyAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tv = view.tv
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapterViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)
            return MyAdapterViewHolder(view)

        }

        override fun onBindViewHolder(holder: MyAdapterViewHolder, position: Int) {
            holder.tv.text = data[position]
        }

        override fun getItemCount() = data.size
    }

    override fun onScrollChange(v: View?, scrollY: Int, oldScrollY: Int) {

    }
}