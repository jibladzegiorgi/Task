package com.giorgi.jibladze.football.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.giorgi.jibladze.football.R
import com.giorgi.jibladze.football.createBadge
import com.giorgi.jibladze.football.ui.match.MatchesFragment
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        bottomNavigationView.setOnNavigationItemSelectedListener {
//
//        }

        bottomNavigationView.createBadge(R.id.favorites,3)
        openFragment()

    }

    private fun openFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                MatchesFragment.newInstance()
            )
            .commit()
    }

}
