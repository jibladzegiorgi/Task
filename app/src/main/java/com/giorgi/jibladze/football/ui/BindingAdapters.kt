package com.giorgi.jibladze.football.ui

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giorgi.jibladze.football.ui.adapter.OverviewAdapter
import com.giorgi.jibladze.football.Utils
import com.giorgi.jibladze.football.model.ViewMatchAction
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
//    Picasso.get().load(url).error(error).into(view)
    Picasso.get().load(url).into(view)
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setTimeTimeStamp")
fun matchTime(view: TextView, time: Long?) {
    time?.let { view.text = Utils.getDateTimeFromTimeStamp(time = it) + " " }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setBallPossession")
fun setBallPossession(view: TextView, text: String?) {
    text?.let { view.text = "$text%" }
}

@BindingAdapter(
    value = ["setBallPossessionProgressForTeam1"
//        , "setBallPossessionProgressForTeam1"
    ], requireAll = true
)
fun setBallPossessionProgress(
    view: ProgressBar,
    text1: String?
) {
    if (text1 != null ) {
        view.progress=text1.toInt()
//        view.progress=80
    }
}

@BindingAdapter(value = ["matchActionItems"])
fun setData(
    recyclerView: RecyclerView,
    list: List<ViewMatchAction>?
) {
    val listForAdapter=list?.toMutableList()
    listForAdapter?.add(0,ViewMatchAction(isHeader = true))
    recyclerView.adapter?.let {adapter->
        if (adapter is OverviewAdapter){
            adapter.run {
                run loop@{
                    list?.forEachIndexed { index, viewMatchAction ->
                        viewMatchAction.actionTime?.toInt()?.let {
                            if (it > 45) {
                                listForAdapter?.add(index,ViewMatchAction(isHeader = true))
                                return@loop
                            }
                        }

                    }
                }
                this.submitList(listForAdapter)
            }
        }
    }
}