package com.giorgi.jibladze.football.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giorgi.jibladze.football.R
import com.giorgi.jibladze.football.databinding.ActionHeaderBinding
import com.giorgi.jibladze.football.databinding.MatchActionItemViewBinding
import com.giorgi.jibladze.football.model.ViewMatchAction
import kotlinx.android.synthetic.main.action_header.view.*
import kotlinx.android.synthetic.main.match_action_item_view.view.*

class OverviewAdapter(
    private val half1: String?,
    private val half2: String?
) : ListAdapter<ViewMatchAction,
        OverviewAdapter.ViewHolder>(SummariesDiff) {

    override fun getItemViewType(position: Int): Int {
        if (getItem(position).isHeader) {
            return R.layout.action_header
        }
        return R.layout.match_action_item_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewType,
                parent, false
            )
            , half1, half2
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val bindingObject: ViewDataBinding,
        private val half1: String?,
        private val half2: String?
    ) :
        RecyclerView.ViewHolder(bindingObject.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: ViewMatchAction) {
            if (bindingObject is ActionHeaderBinding) {
                if (adapterPosition == 0) {
                    itemView.half_text_view.text = "1st Half"
                    itemView.score.text = half1
                } else {
                    itemView.half_text_view.text="2st Half"
                    itemView.score.text = half2
                }
            }

            if (bindingObject is MatchActionItemViewBinding) {
                //მარცხენა გუნდის ლისტი
                itemView.match_action_left_recycler_view.layoutManager =
                    LinearLayoutManager(itemView.context)
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
}

object SummariesDiff : DiffUtil.ItemCallback<ViewMatchAction>() {
    override fun areItemsTheSame(oldItem: ViewMatchAction, newItem: ViewMatchAction): Boolean {
        return oldItem.actionTime == newItem.actionTime &&
                arrayOf(oldItem.team1Action).contentDeepEquals(arrayOf(newItem.team1Action))
    }

    override fun areContentsTheSame(oldItem: ViewMatchAction, newItem: ViewMatchAction) =
        oldItem == newItem
}