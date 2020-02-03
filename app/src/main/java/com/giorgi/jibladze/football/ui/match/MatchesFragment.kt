package com.giorgi.jibladze.football.ui.match

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.giorgi.jibladze.football.*
import com.giorgi.jibladze.football.databinding.MatchesFragmentBinding
import com.giorgi.jibladze.football.model.GoalType.*
import com.giorgi.jibladze.football.model.ViewMatch
import com.giorgi.jibladze.football.model.ViewMocky
import com.giorgi.jibladze.football.ui.InterrnetConnectionErrorFragment
import com.giorgi.jibladze.football.ui.LoaderFragment
import com.giorgi.jibladze.football.ui.overview.OverviewFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.match_header.*
import kotlinx.android.synthetic.main.matches_fragment.*
import javax.inject.Inject


class MatchesFragment : DaggerFragment() {

    private var rightTeam_half1GoalScore = 0
    private var rightTeam_half2GoalScore = 0
    private var leftTeam_half1GoalScore = 0
    private var leftTeam_half2GoalScore = 0
    private var team2GoalCount = 0
    private var team1GoalCount = 0

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var vm: MatchesViewModel
    private lateinit var bindingView: MatchesFragmentBinding

    private val prograssBarFragment: Fragment by lazy {
        LoaderFragment.createInstance()
    }

    private val connectionErrorFragment: InterrnetConnectionErrorFragment by lazy {
        InterrnetConnectionErrorFragment.createInstance()
    }

    companion object {
        fun newInstance() = MatchesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<MatchesFragmentBinding>(
            inflater,
            R.layout.matches_fragment, container, false
        ).also {
            bindingView = it
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = viewModelProvider(viewModelFactory)

        review_container.post {
            (review_container.background as GradientDrawable).also {
                it.cornerRadius = review_container.height.div(2).toFloat()
            }
        }

        with(vm) {
            mockyLiveData.observe(viewLifecycleOwner, Observer {
                changeScore(it)
                bindingView.match = it?.match
                bindingView.executePendingBindings()
                it.match?.let { act ->
                    openFragment(
                        act,
                        "$leftTeam_half1GoalScore:$leftTeam_half2GoalScore",
                        "$rightTeam_half1GoalScore:$rightTeam_half2GoalScore"
                    )
                }
            })

            loaderLiveData.observe(viewLifecycleOwner, Observer {
                if (it) loadFragment(R.id.root_view, prograssBarFragment)
                else removeFragment(prograssBarFragment)
            })

            loadeInternetErrorFragment.observe(viewLifecycleOwner, Observer {
                if (it) loadFragment(R.id.root_view, connectionErrorFragment)
                else removeFragment(connectionErrorFragment)
            })
        }

        connectionErrorFragment.setListener(View.OnClickListener {
            vm.loadData()
        })
    }

    private fun changeScore(data: ViewMocky) {
        data.match?.matchSummary?.matchActionList?.forEach {
            it.team1Action?.forEach { team1 ->

                when (team1.action?.goalType) {

                    GOAL -> {
                        team1GoalCount++
                        it.actionTime?.toInt()?.let { time ->
                            if (time < 45) {
                                leftTeam_half1GoalScore++
                            } else {
                                leftTeam_half2GoalScore++
                            }
                        }
                    }
                    OWN_GOAL -> {
                        team1GoalCount++
                        it.actionTime?.toInt()?.let { time ->
                            if (time < 45) {
                                leftTeam_half1GoalScore++
                            } else {
                                leftTeam_half2GoalScore++
                            }
                        }
                    }
                }
            }
            it.team2Action?.forEach { team1 ->

                when (team1.action?.goalType) {
                    GOAL -> {
                        it.actionTime?.toInt()?.let { time ->
                            if (time < 45) {
                                rightTeam_half1GoalScore++
                            } else {
                                rightTeam_half2GoalScore++
                            }
                        }
                        team2GoalCount++
                    }
                    OWN_GOAL -> {
                        it.actionTime?.toInt()?.let { time ->
                            if (time < 45) {
                                rightTeam_half1GoalScore++
                            } else {
                                rightTeam_half2GoalScore++
                            }
                        }
                        team2GoalCount++
                    }
                }
            }
        }

        team1_score.text = team1GoalCount.toString()
        team1_scoree.text = team2GoalCount.toString()
    }

    private fun openFragment(
        matchSummary: ViewMatch,
        s1: String,
        s2: String
    ) {
        childFragmentManager.beginTransaction()
            .replace(R.id.review_fragment_container, OverviewFragment.newInstance(matchSummary,s1,s2))
            .commit()
    }

}
