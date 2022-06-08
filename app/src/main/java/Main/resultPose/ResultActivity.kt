package Main.resultPose

import Main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import com.example.vision_exam.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore

class ResultActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    private val fl: FrameLayout by lazy{
        findViewById(R.id.fl_container)
    }
    private val bn: BottomNavigationView by lazy{
        findViewById(R.id.bnv_main)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("", "시작")

        //database = FirebaseDatabase.getInstance("종강").reference



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportFragmentManager.beginTransaction().add(fl.id, resultFragment1()).commit()
        bn.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.first->{
                    supportFragmentManager.beginTransaction().replace(fl.id, resultFragment1()).commit()
                    true
                }
                else -> {
                    supportFragmentManager.beginTransaction().replace(fl.id, CompareFragment()).commit()
                    true
                }

            }

        }

    }
}