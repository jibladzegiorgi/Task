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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giorgi.jibladze.football.databinding.MatchActionItemViewBinding
import com.giorgi.jibladze.football.model.GoalType
import com.giorgi.jibladze.football.model.MatchActionType.*
import com.giorgi.jibladze.football.model.MatchTeamType
import com.giorgi.jibladze.football.model.ViewMatchAction
import com.giorgi.jibladze.football.model.ViewTeam1Action
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.left_action.view.*
import kotlinx.android.synthetic.main.left_subtitution.view.*
import kotlinx.android.synthetic.main.match_action_item_view.view.*
import kotlinx.android.synthetic.main.right_action.view.*
import kotlinx.android.synthetic.main.right_subtitution.view.*

class OverviewAdapter(
) : ListAdapter<ViewMatchAction, OverviewAdapter.ViewHolder>(SummariesDiff) {

//    lateinit var leftSubstitution:View
    override fun getItemViewType(position: Int): Int {
        return R.layout.match_action_item_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        leftSubstitution=LayoutInflater.from(parent.context).inflate(R.layout.left_subtitution, null, false)

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

        fun bind(item: ViewMatchAction) {
            if (bindingObject is MatchActionItemViewBinding) {
                resetIncludeViewVisibility()
                leftOrRightTeamAction(item)
            }
        }

        private fun leftOrRightTeamAction(item: ViewMatchAction) {
//            itemView.left_layout_recycler.layoutManager=LinearLayoutManager(itemView.context)
//            itemView.left_layout_recycler.adapter=InnerOverViewAdapter(item.team1Action)

            //team1Action
            item.team1Action?.let { team1 ->

                team1.forEach {
                    //გუნდის ექშები
                    //ვერ მივხვდი კარგად აქ ისედაც პირველი გუნდის Type-ია
                    when (it.teamType) {
                        MatchTeamType.TEAM1 -> {
                            visibleLeftView()
                            team1Action(it, item)
                        }
                        MatchTeamType.TEAM2 -> {
                            visibleRightView()
                            team2Action(it, item)
                        }
                    }
                }

            }

            //team2Action
            item.team2Action?.let { team2 ->
                team2.forEach {
                    //გუნდის ექშები
                    //ვერ მივხვდი კარგად აქ ისედაც პირველი გუნდის Type-ია
                    when (it.teamType) {
                        MatchTeamType.TEAM1 -> {
                            visibleLeftView()
                            team1Action(it, item)
                        }
                        MatchTeamType.TEAM2 -> {
                            team2Action(it, item)
                            visibleRightView()
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

            itemView.right_goal_name.text = it.action?.player?.playerName

            loadPlayerImage(
                it.action?.player?.playerImage,
                itemView.right_player_icon
            )

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
                            goal(itemView.icon_right)
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
                            ownGoal(itemView.icon_right)
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
                    yellowCard(itemView.icon_right)
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
                    redCard(itemView.icon_right)
                }

                SUBSTITUTION -> {
                    team2Substitution(item)
                }
            }
        }


        @SuppressLint("SetTextI18n")
        private fun team1Action(
            it: ViewTeam1Action,
            item: ViewMatchAction
        ) {
            //რა ექშენი მოხდა ....არ იმუშავებს იმიტომ რომ შეიძლება ერთიდაიგივე დროს შეიძელბა ორივე გუნდმა გააკეთოს ექშენი

            itemView.goal_name.text = it.action?.player?.playerName

            loadPlayerImage(
                it.action?.player?.playerImage,
                itemView.left_player_icon
            )

            when (it.actionType) {
                GOAL -> {
                    when (it.action?.goalType) {
                        GoalType.GOAL -> {

                            setGoalTextAndColor(
                                item,
                                itemView.left_time,
                                " Goals by",
                                ContextCompat.getColor(
                                    itemView.context,
                                    R.color._B0B0B0
                                )
                            )
                            goal(itemView.icon_left)
                        }
                        GoalType.OWN_GOAL -> {
                            setGoalTextAndColor(
                                item,
                                itemView.left_time,
                                " Own Goal",
                                ContextCompat.getColor(
                                    itemView.context,
                                    R.color._FF0000
                                )
                            )
                            ownGoal(itemView.icon_left)
                        }
                    }
                }
                YELLOW_CARD -> {
                    setGoalTextAndColor(
                        item,
                        itemView.left_time,
                        " Tripping",
                        ContextCompat.getColor(
                            itemView.context,
                            R.color._B0B0B0
                        )
                    )
                    yellowCard(itemView.icon_left)
                }
                RED_CARD -> {
                    setGoalTextAndColor(
                        item,
                        itemView.left_time,
                        " Tripping",
                        ContextCompat.getColor(
                            itemView.context,
                            R.color._B0B0B0
                        )
                    )
                    redCard(itemView.icon_left)
                }
                SUBSTITUTION -> {
                    team1Substitution(item)
                }
            }
        }

        @SuppressLint("SetTextI18n")
        private fun team2Substitution(
            item: ViewMatchAction
        ) {
            goneRightActionLayout()

            itemView.right_substitution_layout.visibility = View.VISIBLE
            itemView.substitution_right_time.visibility = View.VISIBLE
            itemView.substitution_right_time.text = item.actionTime + " Substitution"
            item.team2Action?.forEach { team2Substitution ->
                team2Substitution.action?.player1?.let {
                    itemView.substitution_right_in_player_name.visibility = View.VISIBLE
                    itemView.substitution_right_in_player_name.text = it.playerName
                }
                team2Substitution.action?.player2?.let {
                    itemView.substitution_right_out_player_name.visibility = View.VISIBLE
                    itemView.substitution_right_out_player_name.text = it.playerName
                }
            }
        }

        @SuppressLint("SetTextI18n")
        private fun team1Substitution(
            item: ViewMatchAction
        ) {
            goneLeftActionLayout()

            itemView.left_substitution_layout.visibility = View.VISIBLE
            itemView.substitution_left_time.visibility = View.VISIBLE
            itemView.substitution_left_time.text = item.actionTime + " Substitution"
            item.team1Action?.forEach { team1Substitution ->
                team1Substitution.action?.player1?.let {
                    itemView.substitution_in_player_name.visibility = View.VISIBLE
                    itemView.substitution_in_player_name.text = it.playerName
                }
                team1Substitution.action?.player2?.let {
                    itemView.substitution_out_player_name.visibility = View.VISIBLE
                    itemView.substitution_out_player_name.text = it.playerName
                }
            }
        }

        private fun goneLeftActionLayout() {
            itemView.left_layout.visibility = View.GONE

        }

        private fun goneRightActionLayout() {
            itemView.right_layout.visibility = View.GONE
        }

        private fun resetIncludeViewVisibility() {
            itemView.right_layout.visibility = View.GONE
            itemView.right_substitution_layout.visibility = View.GONE
            itemView.left_substitution_layout.visibility = View.GONE
            itemView.left_layout.visibility = View.GONE
        }

        private fun visibleLeftView() {
            itemView.left_layout.visibility = View.VISIBLE
        }

        private fun visibleRightView() {
            itemView.right_layout.visibility = View.VISIBLE
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

        //url არ ვარგა
        private fun loadPlayerImage(
            playerImageUrl: String?,
            rightPlayerIcon: AppCompatImageView
        ) {
            //მოსული სურათის მისამართი არ მუშაობს
            val testUrl =
                "https://images2.minutemediacdn.com/image/upload/c_fill,w_912,h_516,f_auto,q_auto,g_auto/shape/cover/sport/france-v-germany-uefa-nations-league-a-5bca0ac66f0e5b6500000001.jpg"
            Picasso.get().load(testUrl)
                .transform(CircleTransform())
                .into(rightPlayerIcon)
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