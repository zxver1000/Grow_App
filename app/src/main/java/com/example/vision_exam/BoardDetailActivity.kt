package com.example.vision_exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class BoardDetailActivity : AppCompatActivity() {

    companion object{
        const val TAG = "BoardDetailActivity"
    }

    private lateinit var curCalendarInfo: CalendarInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_detail)

        initIntent()
    }

    private fun initIntent() {
        curCalendarInfo = intent.getSerializableExtra("info") as CalendarInfo

        Log.e(TAG, "initIntent: ${curCalendarInfo}", )

    }
}