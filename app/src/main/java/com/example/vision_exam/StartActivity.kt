package com.example.vision_exam

import Main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class StartActivity : AppCompatActivity() {

    private val fl: FrameLayout by lazy{
        findViewById(R.id.fl_container)
    }
    private val bn: BottomNavigationView by lazy{
        findViewById(R.id.bnv_main)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        supportFragmentManager.beginTransaction().add(fl.id, homeFragment()).commit()
        bn.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.first->{
                    supportFragmentManager.beginTransaction().replace(fl.id, homeFragment()).commit()
                    true
                }
                R.id.second->{
                    supportFragmentManager.beginTransaction().replace(fl.id, badgeFragment()).commit()
                    true
                }
                R.id.third->{
                    supportFragmentManager.beginTransaction().replace(fl.id, youtubeFragment()).commit()
                    true

                }
                R.id.fourth->{
                    supportFragmentManager.beginTransaction().replace(fl.id, boardFragment()).commit()
                    true
                }

                else -> {
                    supportFragmentManager.beginTransaction().replace(fl.id, poseFragment()).commit()
                    true
                }

            }


        }

    }
}