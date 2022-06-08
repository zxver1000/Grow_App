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

class SignUpFormActivity : AppCompatActivity() {
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
        builder.setSpan(colorSpan, 22, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
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

        val email = intent.getStringExtra("EMAIL3")

        val C_button = findViewById<Button>(R.id.signup_form_continueButton)
        val B_button = findViewById<Button>(R.id.signup_form_skipButton)
        C_button.setOnClickListener {

            val intent = Intent(this, StartActivity::class.java)
            intent.putExtra("EMAIL4", email)
            startActivity(intent)
        }
        B_button.setOnClickListener {
            val intent = Intent(this, SignUpLevelActivity::class.java)
            startActivity(intent)
        }
    }
}