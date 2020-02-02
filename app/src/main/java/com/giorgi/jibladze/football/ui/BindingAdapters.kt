package com.giorgi.jibladze.football.ui

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giorgi.jibladze.football.OverviewAdapter
import com.giorgi.jibladze.football.Utils
import com.giorgi.jibladze.football.model.ViewMatch
import com.giorgi.jibladze.football.model.ViewMatchAction
import com.giorgi.jibladze.football.model.ViewMatchSummaries
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
    recyclerView.adapter?.let {
        if (recyclerView.adapter is OverviewAdapter){
            (it as OverviewAdapter).run {
                this.submitList(list)
            }
        }
    }
}