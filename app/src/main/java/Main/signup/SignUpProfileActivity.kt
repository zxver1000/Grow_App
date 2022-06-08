package Main.signup

import Main.mypageFragment
import android.content.Context
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
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.vision_exam.R
import com.example.vision_exam.preference.PreferenceUtils
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate

/*
   사용자 회원가입 - 1. 프로필 입력 화면
 */
class SignUpProfileActivity : AppCompatActivity() {
    var firebaseStore = FirebaseFirestore.getInstance() //firebase 연동


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_profile)

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
            val firstAccessDate : String = LocalDate.now().toString() //사용자 처음 접속 날짜
            val name = editName.text.toString()
            val nickName = editNickName.text.toString()
            val email = editEmail.text.toString()

            MyApplication.prefs.myEditText = email
            //회원정보 저장 - firebase에 추가
            val user = hashMapOf(
                "name" to name,
                "nickName" to nickName,
                "email" to email,
                "firstAccessDate" to firstAccessDate,
                "poseActiveNum" to 0,
                "calenderRecordNum" to 0,
                "youtubeWatchNum" to 0
            )
            firebaseStore.collection("회원정보").document(email).set(user)

//            val MypageFragment:mypageFragment = mypageFragment()
//            val bundle:Bundle = Bundle()
//            bundle.putString("email", email)
//            MypageFragment.arguments = bundle
//
//            Log.d("TEST",email)

            val intent = Intent(this, SignUpBodyPartActivity::class.java)
            intent.putExtra("EMAIL",email)

            startActivity(intent)
          }
        }
    }
