package com.giorgi.jibladze.football

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giorgi.jibladze.football.databinding.MatchActionItemViewBinding
import com.giorgi.jibladze.football.model.GoalType
import com.giorgi.jibladze.football.model.MatchActionType.*
import com.giorgi.jibladze.football.model.MatchTeamType
import com.giorgi.jibladze.football.model.ViewMatchAction
import com.giorgi.jibladze.football.model.ViewTeam1Action
import kotlinx.android.synthetic.main.left_action.view.*
import kotlinx.android.synthetic.main.match_action_item_view.view.*
import kotlinx.android.synthetic.main.right_action.view.*

class OverviewAdapter(
) : ListAdapter<ViewMatchAction, OverviewAdapter.ViewHolder>(SummariesDiff) {


    //უნდა გავარკვიო რომელი ექშენია
    override fun getItemViewType(position: Int): Int {
        getItem(position).team1Action?.find {
            it.actionType == SUBSTITUTION
        }?.also {
            return R.layout.match_action_substitution_item_view
        }
        return R.layout.match_action_item_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), viewType,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val bindingObject: ViewDataBinding) :
        RecyclerView.ViewHolder(bindingObject.root) {

        private val viewLIst = arrayListOf(
            R.id.card_left, R.id.goal_left,
            R.id.card_right, R.id.goal_right
        )

        fun bind(item: ViewMatchAction) {
            if (bindingObject is MatchActionItemViewBinding) {
                item.team1Action?.let { team1 ->
                    team1.forEach {

                        //გუნდის ექშები
                        //ვერ მივხვდი კარგად აქ ისედაც პირველი გუნდის Type-ია
                        when (it.teamType) {
                            MatchTeamType.TEAM1 -> {
                                team1Action(it, item)
                            }
                            MatchTeamType.TEAM2 -> {
                                team2Action(it, item)
                            }
                        }
                    }

                }
                item.team2Action?.let { team2 ->
                    team2.forEach {
                        //გუნდის ექშები
                        //ვერ მივხვდი კარგად აქ ისედაც პირველი გუნდის Type-ია
                        when (it.teamType) {
                            MatchTeamType.TEAM1 -> {
                                team1Action(it, item)
                            }
                            MatchTeamType.TEAM2 -> {
                                team2Action(it, item)
                            }
                        }
                    }
                }
            }
        }

        @SuppressLint("SetTextI18n")
        private fun team2Action(
            it: ViewTeam1Action,
            item: ViewMatchAction
        ) {
            itemView.left_layout.visibility = View.GONE
            itemView.right_layout.visibility = View.VISIBLE

            itemView.right_goal_name.text = it.action?.player?.playerName

            loadPlayerImage(it.action?.player?.playerImage,
                itemView.right_player_icon)

            //რა ექშენი მოხდა
            when (it.actionType) {
                GOAL -> {
                    when (it.action?.goalType) {
                        GoalType.GOAL -> {
                            setGoalTextAndColor(
                                item,
                                itemView.right_goal_time,
                                " Goals by",
                                ContextCompat.getColor(
                                    itemView.context,
                                    R.color._B0B0B0
                                )
                            )
                            goal(itemView.goal_right)
                        }
                        GoalType.OWN_GOAL -> {
                            setGoalTextAndColor(
                                item,
                                itemView.right_goal_time,
                                " Own Goal",
                                ContextCompat.getColor(
                                    itemView.context,
                                    R.color._FF0000
                                )
                            )
                            ownGoal(itemView.goal_right)
                        }
                    }
                }
                YELLOW_CARD -> {
                    setGoalTextAndColor(
                        item,
                        itemView.right_goal_time,
                        " Tripping",
                        ContextCompat.getColor(
                            itemView.context,
                            R.color._B0B0B0
                        )
                    )
                    yellowCard(itemView.card_right)
                }
                RED_CARD -> {
                    setGoalTextAndColor(
                        item,
                        itemView.right_goal_time,
                        " Tripping",
                        ContextCompat.getColor(
                            itemView.context,
                            R.color._B0B0B0
                        )
                    )
                    redCard(itemView.card_right)
                }
                SUBSTITUTION -> {

                }
            }
        }

        @SuppressLint("SetTextI18n")
        private fun team1Action(
            it: ViewTeam1Action,
            item: ViewMatchAction
        ) {
            //რა ექშენი მოხდა ....არ იმუშავებს იმიტომ რომ შეიძლება ერთიდაიგივე დროს შეიძელბა ორივე გუნდმა გააკეთოს ექშენი
            itemView.left_layout.visibility = View.VISIBLE
            itemView.right_layout.visibility = View.INVISIBLE

            itemView.goal_name.text = it.action?.player?.playerName

            loadPlayerImage(it.action?.player?.playerImage,
                itemView.left_player_icon)

            when (it.actionType) {
                GOAL -> {
                    when (it.action?.goalType) {
                        GoalType.GOAL -> {

                            setGoalTextAndColor(
                                item,
                                itemView.left_layout.goal_time,
                                " Goals by",
                                ContextCompat.getColor(
                                    itemView.context,
                                    R.color._B0B0B0
                                )
                            )
                            goal(itemView.left_layout.goal_left)
                        }
                        GoalType.OWN_GOAL -> {
                            setGoalTextAndColor(
                                item,
                                itemView.left_layout.goal_time,
                                " Own Goal",
                                ContextCompat.getColor(
                                    itemView.context,
                                    R.color._FF0000
                                )
                            )
                            ownGoal(itemView.left_layout.goal_left)
                        }
                    }
                }
                YELLOW_CARD -> {
                    setGoalTextAndColor(
                        item,
                        itemView.left_layout.goal_time,
                        " Tripping",
                        ContextCompat.getColor(
                            itemView.context,
                            R.color._B0B0B0
                        )
                    )
                    yellowCard(itemView.left_layout.card_left)
                }
                RED_CARD -> {
                    setGoalTextAndColor(
                        item,
                        itemView.left_layout.goal_time,
                        " Tripping",
                        ContextCompat.getColor(
                            itemView.context,
                            R.color._B0B0B0
                        )
                    )
                    redCard(itemView.left_layout.card_left)
                }
                SUBSTITUTION -> {

                }
            }
        }

        private fun visibleVIew(view: View) {
            view.visibility = View.VISIBLE
            viewLIst.forEach {
                if (it != view.id) {
                    itemView.findViewById<View>(it).visibility = View.GONE
                }
            }
        }

        @SuppressLint("SetTextI18n")
        private fun setGoalTextAndColor(
            item: ViewMatchAction,
            view: AppCompatTextView,
            text: String,
            color: Int
        ) {
            view.text = item.actionTime + text
            view.setTextColor(color)
        }

        private fun yellowCard(view: AppCompatImageView) {
            visibleVIew(view)
            view.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_yellow_card))
        }

        private fun redCard(view: AppCompatImageView) {
            visibleVIew(view)
            view.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_red_card))
        }

        private fun ownGoal(view: AppCompatImageView) {
            visibleVIew(view)
            view.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_own_goal))
        }

        private fun goal(view: AppCompatImageView) {
            visibleVIew(view)
            view.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_goal))
        }

        //url არ ვარგა
        private fun loadPlayerImage(
            playerImageUrl: String?,
            rightPlayerIcon: AppCompatImageView
        ) {
//            Picasso.get().load(playerImageUrl).into(rightPlayerIcon)
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