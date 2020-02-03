package com.giorgi.jibladze.football.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.giorgi.jibladze.football.R

class LoaderFragment : Fragment() {

    companion object {
        fun createInstance()=LoaderFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_loader,container,false)
    }

}