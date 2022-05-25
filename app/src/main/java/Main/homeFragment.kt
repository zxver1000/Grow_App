package Main

import Main.home.SubActivity01
import Main.home.SubActivity02
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.vision_exam.MainActivity
import com.example.vision_exam.R


class homeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val button_info = root.findViewById<Button>(R.id.exercise_info)
        val button_rec = root.findViewById<Button>(R.id.exercise_rec)

        //버튼부분 수정 필요
        button_rec?.setOnClickListener{
            val intent = Intent(activity, SubActivity01::class.java)
            startActivity(intent)
        }
        button_info?.setOnClickListener{
            val intent = Intent(activity, SubActivity02::class.java)
            startActivity(intent)
        }

        return root
    }


}