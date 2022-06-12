package Main

import Main.home.SubActivity01
import Main.home.SubActivity02
import Main.signup.SignUpLevelActivity
import android.content.Intent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.vision_exam.MainActivity
import com.example.vision_exam.R


class homeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val email1 = arguments?.getString("email")
        val nickname1 = arguments?.getString("nickName")
        val bodypart1 = arguments?.getString("bodypart")
        val level1 = arguments?.getString("level")
        val date1 = arguments?.getString("date")
        val shape1 = arguments?.getString("shape")
        val firstAccessDay = arguments?.getString("firstAccessDay5")

        val isOne = arguments?.getBoolean("isOne",false)

        if (isOne == true){
            Toast.makeText(activity, "You have acquired a new badge! \n Please check the badge on My Page!", Toast.LENGTH_SHORT).show()
        }

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val button_info = root.findViewById<Button>(R.id.exercise_info)
        val button_rec = root.findViewById<Button>(R.id.exercise_rec)

        val nick_text=root.findViewById<TextView>(R.id.nick_text)
        val email_text=root.findViewById<TextView>(R.id.email_text)
        val level_text=root.findViewById<TextView>(R.id.level_text)
        val shape_text=root.findViewById<TextView>(R.id.shape_text)
        val accessday_text = root.findViewById<TextView>(R.id.accessDay_text)

        button_rec?.setOnClickListener{
            val intent = Intent(activity, SubActivity01::class.java)
            intent.putExtra("email2",email1)
            startActivity(intent)
        }
        button_info?.setOnClickListener{
            val intent = Intent(activity, SubActivity02::class.java)
            startActivity(intent)
        }

        email_text.setText(email1)
        nick_text.setText(nickname1)
        level_text.setText(level1)
        shape_text.setText(shape1)
        accessday_text.setText(firstAccessDay)
        //shape_text

        return root
    }


}