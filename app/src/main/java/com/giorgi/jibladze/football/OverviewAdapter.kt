package com.giorgi.jibladze.football

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giorgi.jibladze.football.model.ViewMatchAction
import com.giorgi.jibladze.football.ui.adapter.InnerOverViewLeftAdapter
import com.giorgi.jibladze.football.ui.adapter.InnerOverViewRightAdapter
import kotlinx.android.synthetic.main.match_action_item_view.view.*

class OverviewAdapter : ListAdapter<ViewMatchAction, OverviewAdapter.ViewHolder>(SummariesDiff) {

    override fun getItemViewType(position: Int): Int {
        return R.layout.match_action_item_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                viewType,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        fun bind(item: ViewMatchAction) {
            //მარცხენა გუნდის ლისტი
            itemView.match_action_left_recycler_view.layoutManager =LinearLayoutManager(itemView.context)
            itemView.match_action_left_recycler_view.adapter =
                InnerOverViewLeftAdapter(
                    item.team1Action,
                    item.actionTime
                )

            //მარჯვენა გუნდის ლისტი
            itemView.match_action_right_recycler_view.layoutManager =
                LinearLayoutManager(itemView.context)
            itemView.match_action_right_recycler_view.adapter =
                InnerOverViewRightAdapter(
                    item.team2Action,
                    item.actionTime
                )
        }
    }
}

object SummariesDiff : DiffUtil.ItemCallback<ViewMatchAction>() {
    override fun areItemsTheSame(oldItem: ViewMatchAction, newItem: ViewMatchAction): Boolean {
        return oldItem.actionTime == newItem.actionTime &&
                arrayOf(oldItem.team1Action).contentDeepEquals(arrayOf(newItem.team1Action))
    }

    override fun areContentsTheSame(oldItem: ViewMatchAction, newItem: ViewMatchAction) =
        oldItem == newItem
}