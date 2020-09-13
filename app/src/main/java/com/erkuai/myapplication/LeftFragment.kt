package com.erkuai.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.erkuai.myapplication.widget.ConsecutiveScrollerLayout
import kotlinx.android.synthetic.main.fragment_left.*
import kotlin.math.abs

class LeftFragment : Fragment(), ConsecutiveScrollerLayout.OnScrollChangeListener {


    var position = IntArray(2)
    var out_position = IntArray(2)

    var measure = true

    var height = 0
    var offset = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_left, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recycler_1.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        val data = arrayListOf<String>()
        for (index in 0 until 40) {
            data.add("第 $index 个")
        }
        recycler_1.adapter = MainActivity.MyAdapter(data)


        left_layout.setOnVerticalScrollChangeListener(this)
        // Log.i("wmkwmk", "set")

    }

    fun getInnerTitle(): LinearLayout {

        return ll_inner_title
    }

    override fun onScrollChange(v: View?, scrollY: Int, oldScrollY: Int) {

        // 向上 scrollY > oldScrollY
        // 向下 scrollY < oldScrollY
        if (activity is MainActivity) {
            val outTitle = (activity as MainActivity).getOutTitle()

            val layout = (activity as MainActivity).getLayout()

            if (measure) {
                ll_inner_title.getLocationOnScreen(position)
                outTitle.getLocationOnScreen(out_position)
                offset = out_position[1] + outTitle.height
                //  Log.i("wmk", "offset: ${offset}")
            }

            if (position[1] <= out_position[1] + outTitle.height && height <= outTitle.height) {  //向上
                measure = false
                height += scrollY - oldScrollY
                layout.stickyOffset = -height


                Log.i("wmk", "上: ${height}")
            }

            if (oldScrollY - scrollY > 0) {// 向下
                measure = true

                // if (position[1]>out_position[1] + outTitle.height){
//                var stickyOffset = layout.stickyOffset
//                height +=  scrollY-oldScrollY
//                if (abs(stickyOffset) <= outTitle.height) {
//
//                }

                if (position[1] > 400){
                    layout.stickyOffset = 0
                    height = 0

                }
                Log.i("wmk", "下: ---${height}---${out_position[1] + outTitle.height}")

                //   }

            }
//            if (oldScrollY - scrollY > 0) {// 向下
//               // measure = true
//                Log.i("wmk", "下: ${position[1]}")
//                Log.i("wmk", "下2: ${out_position[1] + outTitle.height}")
//
//                if (position[1] < offset) {
//                    measure = true
//                }else{
////                    measure = false
////
////                    height = 0
////                    layout.stickyOffset = 0
//                }
//
//            }

        }

    }


}