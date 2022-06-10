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
    private var youtubeCount = 0
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badge)
        initLayout()
        fbpath.addSnapshotListener(EventListener<DocumentSnapshot> {snapshot, e->
            if (snapshot != null) {
                youtubeCount = snapshot.data!!["youtubeWatchNum"].toString().toInt()
                Log.d("YOUTUBECOUNT",youtubeCount.toString())
            }
            Log.d("YOUTUBECOUNT",youtubeCount.toString())
        })
        Log.d("YOUTUBE COUNT",youtubeCount.toString())
        val youtubeBadgeOne = findViewById<ImageView>(R.id.badge_youtube_one)
        val youtubeBadgeThree = findViewById<ImageView>(R.id.badge_youtube_three)
        val youtubeBadgeFive = findViewById<ImageView>(R.id.badge_youtube_five)

        if (youtubeCount>=1)
        {
            youtubeBadgeOne.setImageResource(R.drawable.badge_youtube_one)
            Log.d("COUNTTEST","1")
            if(youtubeCount>=3)
            {
                youtubeBadgeThree.setImageResource(R.drawable.badge_youtube_three)
                Log.d("COUNTTEST","3")
                if(youtubeCount>=5)
                {
                    youtubeBadgeFive.setImageResource(R.drawable.badge_youtube_five)
                    Log.d("COUNTTEST","5")
                }
            }

        }


    }

    private fun initLayout() {
        val B_button = findViewById<ImageButton>(R.id.badge_backButton)
        B_button.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.mypage_framelayout, mypageFragment()).commit()
        }




    }





}