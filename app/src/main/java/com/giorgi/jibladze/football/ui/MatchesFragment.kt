package com.giorgi.jibladze.football.ui

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.giorgi.jibladze.football.R
import kotlinx.android.synthetic.main.matches_fragment.*


class MatchesFragment : Fragment() {

    companion object {
        fun newInstance() = MatchesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.matches_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        review_container.post {
            (review_container.background as GradientDrawable).also {
                it.cornerRadius= review_container.height.div(2).toFloat()
            }
        }

    }

}
