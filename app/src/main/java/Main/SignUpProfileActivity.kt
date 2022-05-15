package Main

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.vision_exam.R

/*
   사용자 회원가입 - 1. 프로필 입력 화면
 */
class SignUpProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_profile)
        initLayout()
    }

    private fun initLayout() {
        //textView 일부분 색 변경
        val title = findViewById<TextView>(R.id.signup_profile_title)
        val textData:String = title.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorSpan = ForegroundColorSpan(Color.parseColor("#6842FF"))
        builder.setSpan(colorSpan,7,10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        title.text = builder

        val C_button = findViewById<Button>(R.id.signup_profile_continueButton)
        C_button.setOnClickListener {
            val intent = Intent(this,SignUpBodyPartActivity::class.java)
            startActivity(intent)
          }
        }
    }
