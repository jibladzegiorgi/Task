package com.giorgi.jibladze.football.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import com.giorgi.jibladze.football.R
import kotlinx.android.synthetic.main.review_view.view.*

@SuppressLint("ViewConstructor")
class ReviewView :
    FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    init {
        View.inflate(context, R.layout.review_view,this).also {
            overview_btn.isSelected=true
            overview_btn.setOnClickListener {
                    overview_btn.isSelected=true
                    disableItems(arrayListOf(statistic_btn,line_up_btn))
            }
            statistic_btn.setOnClickListener {
                    statistic_btn.isSelected=true
                    disableItems(arrayListOf(overview_btn,line_up_btn))
            }
            line_up_btn.setOnClickListener {
                    disableItems(arrayListOf(overview_btn,statistic_btn))
                    line_up_btn.isSelected=true
            }
        }
    }

    private fun disableItems(viewList: ArrayList<AppCompatTextView>) {
        viewList.forEach {
            it.isSelected=false
        }
    }

}