package com.example.vision_exam

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import androidx.appcompat.widget.AppCompatButton

class stopwatchFragment : Fragment() {

    var flag=false
    var inittime=0L
    var pausetime=0L
    lateinit var chronometer:Chronometer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_stopwatch, container, false)
        chronometer=view.findViewById<Chronometer>(R.id.yt_chronometer)
        val startbtn=view.findViewById<AppCompatButton>(R.id.yt_startbtn)
        val stopbtn=view.findViewById<AppCompatButton>(R.id.yt_stopbtn)
        val resetbtn=view.findViewById<AppCompatButton>(R.id.yt_resetbtn)
        startbtn.setOnClickListener {
            flag=true
            chronometer.base= SystemClock.elapsedRealtime()+pausetime
            chronometer.start()
            startbtn.isEnabled=false
            stopbtn.isEnabled=true
            resetbtn.isEnabled=true
        }
        stopbtn.setOnClickListener {
            flag=false
            pausetime=chronometer.base- SystemClock.elapsedRealtime()
            chronometer.stop()
            startbtn.isEnabled=true
            stopbtn.isEnabled=false
            resetbtn.isEnabled=true
        }
        resetbtn.setOnClickListener {
            flag=false
            pausetime=0L
            chronometer.base= SystemClock.elapsedRealtime()
            chronometer.stop()
            startbtn.isEnabled=true
            stopbtn.isEnabled=false
            resetbtn.isEnabled=false
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        if(flag){
            chronometer.base= SystemClock.elapsedRealtime()+pausetime
            chronometer.start()
        }
    }

    override fun onStop() {
        super.onStop()
        if(flag){
            pausetime=chronometer.base- SystemClock.elapsedRealtime()
            chronometer.stop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        flag=false
        pausetime=0L
        chronometer.stop()
    }
}