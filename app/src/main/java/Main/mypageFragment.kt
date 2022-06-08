package Main


import Main.signup.SignUpProfileActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.example.vision_exam.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class mypageFragment() : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val email = arguments?.getString("EMAIL4").toString()

        val root =  inflater.inflate(R.layout.fragment_mypage, container, false)
        val badge_button = root.findViewById<Button>(R.id.badge)
        val mypageName = root.findViewById<TextView>(R.id.mypage_user_name)
        val mypageNickName = root.findViewById<TextView>(R.id.mypage_user_nickname)
        val mypageEmail = root.findViewById<TextView>(R.id.mypage_user_email)


        var firebaseStore = FirebaseFirestore.getInstance() //firebase 연동

        val fbpath = firebaseStore.collection("회원정보").document(email)

        fbpath.addSnapshotListener(EventListener<DocumentSnapshot> {snapshot, e->
            if (snapshot != null) {
                mypageName.text = snapshot.data!!["name"].toString()
                mypageNickName.text = snapshot.data!!["nickName"].toString()
                mypageEmail.text = snapshot.data!!["email"].toString()
            }
        })



        badge_button.setOnClickListener {
            activity?.let {
                val intent = Intent(context, BadgeActivity::class.java)
                startActivity(intent)
            }
        }

        return root
    }

}