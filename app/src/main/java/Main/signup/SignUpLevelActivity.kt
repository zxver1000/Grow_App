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

/*
   사용자 회원가입 - 3. 운동 레벨 선택 화면
 */
class SignUpLevelActivity : AppCompatActivity() {
    var firebaseStore = FirebaseFirestore.getInstance() //firebase 연동
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_level)
        initLaoyut()
    }

    private fun initLaoyut() {
        //textView 일부분 색 변경
        val title = findViewById<TextView>(R.id.signup_level_title)
        val textData:String = title.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorSpan = ForegroundColorSpan(Color.parseColor("#6842FF"))
        builder.setSpan(colorSpan,22,36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        title.text = builder

        val basicButton = findViewById<Button>(R.id.signup_level_basic)
        val interButton = findViewById<Button>(R.id.signup_level_intermediate)
        val seniButton = findViewById<Button>(R.id.signup_level_senior)

        basicButton?.setOnClickListener {
            basicButton?.isSelected = basicButton?.isSelected != true
        }

        interButton?.setOnClickListener {
            interButton?.isSelected = interButton?.isSelected != true
        }

        seniButton?.setOnClickListener {
            seniButton?.isSelected = seniButton?.isSelected != true
        }

        val email = intent.getStringExtra("EMAIL2")!!
        if (email != null) {
            Log.d("TEST3",email)
        }
        val name = intent.getStringExtra("NAME2")!!
        val nickName = intent.getStringExtra("NICKNAME2")!!
        val firstAccessDate = intent.getStringExtra("FIRSTACCESSDATE2")!!
        val bodypart = intent.getStringExtra("BODYPART2")!!

        val C_button = findViewById<Button>(R.id.signup_level_continueButton)
        val B_button = findViewById<Button>(R.id.signup_level_skipButton)
        C_button.setOnClickListener {
            var level:String=""
            ////bodypart 체크박스 id받아오기
            val basicButton = findViewById<Button>(R.id.signup_level_basic)
            val interButton = findViewById<Button>(R.id.signup_level_intermediate)
            val seniButton = findViewById<Button>(R.id.signup_level_senior)

            if(basicButton.isSelected){
                level="Beginner"
            }else if (interButton.isSelected){
                level = "Intermediate"
            }else if (seniButton.isSelected){
                level = "Advanced"
            }

            val f_level = hashMapOf(
                "name" to name,
                "nickName" to nickName,
                "email" to email,
                "firstAccessDate" to firstAccessDate,
                "accessNum" to 0,
                "calenderRecordNum" to 0,
                "youtubeWatchNum" to 0,
                "bodypart" to bodypart,
                "level" to level,
                "shape" to ""

            )
            firebaseStore.collection("회원정보").document(email).set(f_level)

            val intent = Intent(this,SignUpFormActivity::class.java)
            intent.putExtra("EMAIL3",email)
            intent.putExtra("NAME3",name)
            intent.putExtra("NICKNAME3",nickName)
            intent.putExtra("FIRSTACCESSDATE3",firstAccessDate)
            intent.putExtra("BODYPART3",bodypart)
            intent.putExtra("LEVEL3",level)
            startActivity(intent)
        }
        B_button.setOnClickListener {
            val intent = Intent(this,SignUpBodyPartActivity::class.java)
            startActivity(intent)
        }
    }
}