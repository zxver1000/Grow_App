package Main.signup

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
import android.widget.Toast
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
        builder.setSpan(colorSpan,21,28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        title.text = builder

        val editName = findViewById<EditText>(R.id.signup_profile_name)
        val editNickName= findViewById<EditText>(R.id.signup_profile_nickName)
        val editEmail = findViewById<EditText>(R.id.signup_profile_email)

        val C_button = findViewById<Button>(R.id.signup_profile_continueButton)
        C_button.setOnClickListener {
            val name = editName.text.toString()
            val nickName = editNickName.text.toString()
            val email = editEmail.text.toString()


            val intent = Intent(this, SignUpBodyPartActivity::class.java)
            startActivity(intent)
          }
        }
    }
