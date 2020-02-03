package com.giorgi.jibladze.football.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.giorgi.jibladze.football.R
import com.giorgi.jibladze.football.databinding.MatchActionInnerLeftItemViewBinding
import com.giorgi.jibladze.football.loadImage
import com.giorgi.jibladze.football.model.GoalType
import com.giorgi.jibladze.football.model.MatchActionType.*
import com.giorgi.jibladze.football.model.MatchTeamType
import com.giorgi.jibladze.football.model.ViewTeam1Action
import kotlinx.android.synthetic.main.left_action.view.*
import kotlinx.android.synthetic.main.left_subtitution.view.*
import kotlinx.android.synthetic.main.match_action_inner_left_item_view.view.*

class InnerOverViewLeftAdapter(
    private val teamActionList: List<ViewTeam1Action>?,
    private val actionTime: String?
) : RecyclerView.Adapter<InnerOverViewLeftAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.match_action_inner_left_item_view,
                parent,
                false
            ),
            actionTime
        )
    }

    override fun getItemCount(): Int = teamActionList?.size ?: 0


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(teamActionList?.get(position))
    }

    class Holder(
        bindingObject: MatchActionInnerLeftItemViewBinding,
        private val actionTime: String?
    ) : RecyclerView.ViewHolder(bindingObject.root) {

        fun bind(
            item: ViewTeam1Action?
        ) {
            resetIncludeViewVisibility()
            leftOrRightTeamAction(item)
        }

        private fun leftOrRightTeamAction(item: ViewTeam1Action?) {
            //team1Action
            item?.let { team1 ->

                when (team1.teamType) {
                    MatchTeamType.TEAM1 -> {
                        visibleLeftView()
                        team1Action(item)
                    }
                    MatchTeamType.TEAM2 -> {
                        visibleLeftView()
                        team1Action(item)
                    }
                }
            }
        }

        @SuppressLint("SetTextI18n")
        private fun team1Action(
            item: ViewTeam1Action
        ) {

            itemView.goal_name.text = item.action?.player?.playerName

            //url არ ვარგა
            loadImage(
                item.action?.player?.playerImage,
                itemView.left_player_icon
            )

            when (item.actionType) {
                GOAL -> when (item.action?.goalType) {
                    GoalType.GOAL -> {
                        setGoalTextAndColor(
                            itemView.left_time, " Goals by",
                            ContextCompat.getColor(
                                itemView.context,
                                R.color._B0B0B0
                            )
                        )
                        goal(itemView.icon_left)
                    }
                    GoalType.OWN_GOAL -> {
                        setGoalTextAndColor(
                            itemView.left_time, " Own Goal",
                            ContextCompat.getColor(
                                itemView.context,
                                R.color._FF0000
                            )
                        )
                        ownGoal(itemView.icon_left)
                    }
                }
                YELLOW_CARD -> {
                    setGoalTextAndColor(
                        itemView.left_time, " Tripping",
                        ContextCompat.getColor(
                            itemView.context,
                            R.color._B0B0B0
                        )
                    )
                    yellowCard(itemView.icon_left)
                }
                RED_CARD -> {
                    setGoalTextAndColor(
                        itemView.left_time, " Tripping",
                        ContextCompat.getColor(
                            itemView.context,
                            R.color._B0B0B0
                        )
                    )
                    redCard(itemView.icon_left)
                }
                SUBSTITUTION -> {
                    leftTeamSubstitution(item)
                }
            }
        }

        @SuppressLint("SetTextI18n")
        private fun leftTeamSubstitution(
            item: ViewTeam1Action
        ) {
            goneLeftActionLayout()

            itemView.left_substitution_layout.visibility = View.VISIBLE
            itemView.substitution_left_time.visibility = View.VISIBLE
            itemView.substitution_left_time.text = "$actionTime' Substitution"
            item.let { team1Substitution ->
                team1Substitution.action?.player1?.let {
                    loadImage(
                        item.action?.player1?.playerImage,
                        itemView.left_in_player_icon
                    )
                    itemView.substitution_in_player_name.visibility = View.VISIBLE
                    itemView.substitution_in_player_name.text = it.playerName
                }
                team1Substitution.action?.player2?.let {
                    loadImage(
                        item.action?.player2?.playerImage,
                        itemView.left_oout_player_icon
                    )
                    itemView.substitution_out_player_name.visibility = View.VISIBLE
                    itemView.substitution_out_player_name.text = it.playerName
                }
            }
        }

        private fun goneLeftActionLayout() {
            itemView.left_layout.visibility = View.GONE

        }

        private fun resetIncludeViewVisibility() {
            itemView.left_substitution_layout.visibility = View.GONE
            itemView.left_layout.visibility = View.GONE
        }

        private fun visibleLeftView() {
            itemView.left_layout.visibility = View.VISIBLE
        }

        @SuppressLint("SetTextI18n")
        private fun setGoalTextAndColor(
            view: AppCompatTextView,
            text: String,
            color: Int
        ) {
            view.text = "$actionTime'$text"
            view.setTextColor(color)
        }

        private fun yellowCard(view: AppCompatImageView) {
            view.visibility = View.VISIBLE
            itemView.action.visibility = View.VISIBLE
            view.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_yellow_card))
        }

        private fun redCard(view: AppCompatImageView) {
            view.visibility = View.VISIBLE
            itemView.action.visibility = View.VISIBLE
            view.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_red_card))
        }

        private fun ownGoal(view: AppCompatImageView) {
            view.visibility = View.VISIBLE
            itemView.action.visibility = View.VISIBLE
            view.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_own_goal))
        }

        private fun goal(view: AppCompatImageView) {
            view.visibility = View.VISIBLE
            itemView.action.visibility = View.VISIBLE
            view.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_goal))
        }
    }


}