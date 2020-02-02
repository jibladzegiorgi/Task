package com.giorgi.jibladze.football.ui.match

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.giorgi.jibladze.football.R
import com.giorgi.jibladze.football.databinding.MatchesFragmentBinding
import com.giorgi.jibladze.football.model.ViewMatch
import com.giorgi.jibladze.football.ui.overview.OverviewFragment
import com.giorgi.jibladze.football.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.matches_fragment.*
import javax.inject.Inject


class MatchesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var vm: MatchesViewModel
    private lateinit var bindingView: MatchesFragmentBinding

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

        vm.mockyLiveData.observe(viewLifecycleOwner, Observer {
            bindingView.match = it?.match
            bindingView.executePendingBindings()
            it.match?.let { act -> openFragment(act) }
        })
    }

    private fun openFragment(matchSummary: ViewMatch) {
        childFragmentManager.beginTransaction()
            .replace(R.id.review_fragment_container, OverviewFragment.newInstance(matchSummary))
            .commit()
    }

}
