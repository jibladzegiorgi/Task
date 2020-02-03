package com.giorgi.jibladze.football.ui.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.giorgi.jibladze.football.ui.adapter.OverviewAdapter

import com.giorgi.jibladze.football.R
import com.giorgi.jibladze.football.databinding.FragmentOverviewBinding
import com.giorgi.jibladze.football.model.ViewMatch
import kotlinx.android.synthetic.main.fragment_overview.*

class OverviewFragment : Fragment() {

    private lateinit var fragmentOverviewBinding: FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return DataBindingUtil.inflate<FragmentOverviewBinding>(
            inflater,
            R.layout.fragment_overview, container, false
        ).also {
            fragmentOverviewBinding = it
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()

        arguments?.getSerializable(PARAMETER)?.let {
            (it as ViewMatch).run {
                fragmentOverviewBinding.team1 = this.team1
                fragmentOverviewBinding.team2 = this.team2
                fragmentOverviewBinding.viewMatch = this.matchSummary
                fragmentOverviewBinding.executePendingBindings()
            }
        }
    }

    private fun initRecyclerView() {
        match_action_recycler.layoutManager = LinearLayoutManager(context)
        match_action_recycler.adapter = OverviewAdapter(
            arguments?.getString(HALF_1),
            arguments?.getString(HALF_2)
        )
    }


    companion object {

        const val PARAMETER = "parameter"
        const val HALF_1 = "half_1"
        const val HALF_2 = "HALF_2"

        @JvmStatic
        fun newInstance(
            matchSummary: ViewMatch,
            s1: String,
            s2: String
        ) =
            OverviewFragment().also {
                it.arguments = Bundle().also { b ->
                    b.putSerializable(PARAMETER, matchSummary)
                    b.putString(HALF_1, s1)
                    b.putString(HALF_2, s2)
                }
            }
    }
}
