package Main.signup

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.vision_exam.R
import com.example.vision_exam.StartActivity
import com.google.firebase.firestore.FirebaseFirestore

class SignUpFormActivity : AppCompatActivity() {
    var firebaseStore = FirebaseFirestore.getInstance() //firebase 연동
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_form)
        initLaoyut()
    }

    private fun initLaoyut() {
        //textView 일부분 색 변경
        val title = findViewById<TextView>(R.id.signup_form_title)
        val textData: String = title.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorSpan = ForegroundColorSpan(Color.parseColor("#6842FF"))
        builder.setSpan(colorSpan, 24, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        title.text = builder

        val lweightButton = findViewById<Button>(R.id.signup_form_lweight)
        val standardButton = findViewById<Button>(R.id.signup_form_standard)
        val oweightButton = findViewById<Button>(R.id.signup_form_oweight)
        val obesityButton = findViewById<Button>(R.id.signup_form_obesity)

        lweightButton?.setOnClickListener {
            lweightButton?.isSelected = lweightButton?.isSelected != true
        }

        standardButton?.setOnClickListener {
            standardButton?.isSelected = standardButton?.isSelected != true
        }

        oweightButton?.setOnClickListener {
            oweightButton?.isSelected = oweightButton?.isSelected != true
        }

        obesityButton?.setOnClickListener {
            obesityButton?.isSelected = obesityButton?.isSelected != true
        }

        val email = intent.getStringExtra("EMAIL3")!!
        if (email != null) {
            Log.d("TEST4",email)
        }
        val name = intent.getStringExtra("NAME3")!!
        val nickName = intent.getStringExtra("NICKNAME3")!!
        val bodypart = intent.getStringExtra("BODYPART3")!!
        val level = intent.getStringExtra("LEVEL3")!!
        val firstAccessDay= intent.getStringExtra("FirstAccessDay3")!!

        val C_button = findViewById<Button>(R.id.signup_form_continueButton)
        val B_button = findViewById<Button>(R.id.signup_form_skipButton)
        C_button.setOnClickListener {
            var shape=""

            if(lweightButton.isSelected){
                shape = "Low_Weight"
            }else if(standardButton.isSelected){
                shape = "Standard"
            }else if(oweightButton.isSelected){
                shape="Over_Weight"
            }else if(obesityButton.isSelected){
                shape="Obesity"
            }

            val f_shape = hashMapOf(
                "name" to name,
                "nickName" to nickName,
                "email" to email,
                "firstAccessDay" to firstAccessDay,
                "calenderRecordNum" to 0,
                "youtubeWatchNum" to 0,
                "poseActiveNum" to 0,
                "bodypart" to bodypart,
                "level" to level,
                "shape" to shape

            )
            firebaseStore.collection("회원정보").document(email).set(f_shape)

            val intent = Intent(this, StartActivity::class.java)
            intent.putExtra("EMAIL4",email)
            intent.putExtra("NAME4",name)
            intent.putExtra("NICKNAME4",nickName)
            intent.putExtra("BODYPART4",bodypart)
            intent.putExtra("FirstAccessDay4",firstAccessDay)
            intent.putExtra("LEVEL4",level)
            intent.putExtra("SHAPE4",shape)
            startActivity(intent)
        }
        B_button.setOnClickListener {
            val intent = Intent(this, SignUpLevelActivity::class.java)
            startActivity(intent)
        }
    }
}