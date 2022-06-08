package Main.signup

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.vision_exam.R
import com.google.firebase.firestore.FirebaseFirestore

/*
   사용자 회원가입 - 2. 원하는 신체 부위 선택 화면
 */

class SignUpBodyPartActivity : AppCompatActivity() {
    var firebaseStore = FirebaseFirestore.getInstance() //firebase 연동

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_body_part)
        initLayout()

    }

    private fun initLayout() {
        //textView 일부분 색 변경
        val title = findViewById<TextView>(R.id.signup_bodyPart_title)
        val textData:String = title.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorSpan = ForegroundColorSpan(Color.parseColor("#6842FF"))
        builder.setSpan(colorSpan,23,32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        title.text = builder

        val email = intent.getStringExtra("EMAIL")!!
        if (email != null) {
            Log.d("TEST2",email)
        }

        val name = intent.getStringExtra("NAME")!!
        val nickName = intent.getStringExtra("NICKNAME")!!
        val firstAccessDate = intent.getStringExtra("FIRSTACCESSDATE")!!


        val C_button = findViewById<Button>(R.id.signup_bodyPart_continueButton)
        val B_button = findViewById<Button>(R.id.signup_bodyPart_skipButton)

        C_button.setOnClickListener {
            var bodypart:String=""
            ////bodypart 체크박스 id받아오기
            val thigh = findViewById<CheckBox>(R.id.bodyPart_thigh)
            val belly = findViewById<CheckBox>(R.id.bodyPart_belly)
            val side = findViewById<CheckBox>(R.id.bodyPart_side)
            val arm = findViewById<CheckBox>(R.id.bodyPart_arm)
            val back = findViewById<CheckBox>(R.id.bodyPart_back)
            val calf = findViewById<CheckBox>(R.id.bodyPart_calf)
            val shoulder = findViewById<CheckBox>(R.id.bodyPart_shoulder)
            val chest = findViewById<CheckBox>(R.id.bodyPart_chest)
            if(thigh.isChecked){
                bodypart="thigh"
            }else if (belly.isChecked){
                bodypart = "belly"
            }else if (side.isChecked){
                bodypart = "side"
            }else if (arm.isChecked){
                bodypart = "arm"
            }else if (back.isChecked){
                bodypart = "back"
            }else if (calf.isChecked){
                bodypart = "calf"
            }else if (shoulder.isChecked){
                bodypart = "shoulder"
            }else if (chest.isChecked){
                bodypart = "chest"
            }
            val f_bodypart = hashMapOf(
                "name" to name,
                "nickName" to nickName,
                "email" to email,
                "firstAccessDate" to firstAccessDate,
                "accessNum" to 0,
                "calenderRecordNum" to 0,
                "youtubeWatchNum" to 0,
                "bodypart" to bodypart,
                "level" to ""
            )
            firebaseStore.collection("회원정보").document(email).set(f_bodypart)

            val intent = Intent(this, SignUpLevelActivity::class.java)
            intent.putExtra("EMAIL2",email)
            intent.putExtra("NAME2",name)
            intent.putExtra("NICKNAME2",nickName)
            intent.putExtra("FIRSTACCESSDATE2",firstAccessDate)
            intent.putExtra("BODYPART2",bodypart)
            startActivity(intent)
        }
        B_button.setOnClickListener {
            val intent = Intent(this,SignUpProfileActivity::class.java)
            startActivity(intent)
        }


    }
}