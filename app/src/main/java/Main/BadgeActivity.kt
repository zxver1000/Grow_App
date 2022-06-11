package Main

import Main.signup.MyApplication
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.vision_exam.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class BadgeActivity : AppCompatActivity() {


    val firebaseStore = FirebaseFirestore.getInstance()
    val fbpath = firebaseStore.collection("회원정보").document(MyApplication.prefs.myEditText.toString())

    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badge)
        initLayout()
        fbpath.addSnapshotListener(EventListener<DocumentSnapshot> {snapshot, e->
            if (snapshot != null) {
                var youtubeCount = snapshot.data!!["youtubeWatchNum"].toString().toInt()
                var poseCount = snapshot.data!!["poseActiveNum"].toString().toInt()
                var calCount = snapshot.data!!["calenderRecordNum"].toString().toInt()

                val youtubeBadgeOne = findViewById<ImageView>(R.id.badge_youtube_one)
                val youtubeBadgeThree = findViewById<ImageView>(R.id.badge_youtube_three)
                val youtubeBadgeFive = findViewById<ImageView>(R.id.badge_youtube_five)

                val poseBadgeOne = findViewById<ImageView>(R.id.badge_posture_first)
                val poseBadgeFive = findViewById<ImageView>(R.id.badge_posture_five)
                val poseBadgeTen = findViewById<ImageView>(R.id.badge_posture_ten)

                val calBadgeOne = findViewById<ImageView>(R.id.badge_record_first)
                val calBadgeThree = findViewById<ImageView>(R.id.badge_record_thirty)
                val calBadgeTen = findViewById<ImageView>(R.id.badge_record_ten)

                if (youtubeCount>=1)
                {
                    youtubeBadgeOne.setImageResource(R.drawable.badge_youtube_one)
                    if(youtubeCount>=3)
                    {
                        youtubeBadgeThree.setImageResource(R.drawable.badge_youtube_three)
                        if(youtubeCount>=5)
                        {
                            youtubeBadgeFive.setImageResource(R.drawable.badge_youtube_five)
                        }
                    }
                }

                if (poseCount>=1)
                {
                    poseBadgeOne.setImageResource(R.drawable.badge_user_one)
                    if(poseCount>=5)
                    {
                        poseBadgeFive.setImageResource(R.drawable.badge_use_five)
                        if(poseCount>=10)
                        {
                            poseBadgeTen.setImageResource(R.drawable.badge_use_ten)
                        }
                    }
                }

                if (calCount>=1)
                {
                    calBadgeOne.setImageResource(R.drawable.badge_record_first)
                    if(calCount>=3)
                    {
                        calBadgeThree.setImageResource(R.drawable.badge_record_thirty)
                        if(calCount>=10)
                        {
                            calBadgeTen.setImageResource(R.drawable.badge_record_ten)
                        }
                    }
                }
            }

        })




    }

    private fun initLayout() {
        val B_button = findViewById<ImageButton>(R.id.badge_backButton)
        B_button.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.mypage_framelayout, mypageFragment()).commit()
        }




    }





}