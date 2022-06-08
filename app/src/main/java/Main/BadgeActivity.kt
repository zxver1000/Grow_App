package Main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.vision_exam.R


class BadgeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badge)
        initLayout()

    }

    private fun initLayout() {
        val B_button = findViewById<ImageButton>(R.id.badge_backButton)
        B_button.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.mypage_framelayout, mypageFragment()).commit()
        }



    }





}