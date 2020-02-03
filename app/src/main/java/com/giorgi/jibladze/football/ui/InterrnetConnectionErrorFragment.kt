package com.giorgi.jibladze.football.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.giorgi.jibladze.football.R
import kotlinx.android.synthetic.main.fragment_connection_error.*

class InterrnetConnectionErrorFragment : Fragment() {

    private var listener: View.OnClickListener? = null

    companion object {
        fun createInstance() = InterrnetConnectionErrorFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_connection_error, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retry_button.setOnClickListener {
            listener?.onClick(it)
        }
    }

    fun setListener(listener: View.OnClickListener): InterrnetConnectionErrorFragment {
        this.listener = listener
        return this
    }
}