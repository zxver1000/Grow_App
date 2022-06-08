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

/*
   사용자 회원가입 - 2. 원하는 신체 부위 선택 화면
 */

class SignUpBodyPartActivity : AppCompatActivity() {
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

        val email = intent.getStringExtra("EMAIL")
        if (email != null) {
            Log.d("TEST2",email)
        }
        val C_button = findViewById<Button>(R.id.signup_bodyPart_continueButton)
        val B_button = findViewById<Button>(R.id.signup_bodyPart_skipButton)
        C_button.setOnClickListener {
            val intent = Intent(this, SignUpLevelActivity::class.java)
            intent.putExtra("EMAIL2",email)
            startActivity(intent)
        }
        B_button.setOnClickListener {
            val intent = Intent(this,SignUpProfileActivity::class.java)
            startActivity(intent)
        }
    }
}