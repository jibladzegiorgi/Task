package com.giorgi.jibladze.football.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.giorgi.jibladze.football.R
import com.giorgi.jibladze.football.ui.match.MatchesFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        bottomNavigationView.setOnNavigationItemSelectedListener {
//
//        }
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
