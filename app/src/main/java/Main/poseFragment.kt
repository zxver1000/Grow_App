package Main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.vision_exam.MainActivity
import com.example.vision_exam.R


class poseFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root=inflater.inflate(R.layout.fragment_pose, container, false)
        // Inflate the layout for this fragment
        val button:Button=root.findViewById(R.id.button3)
        print("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm")
        button?.setOnClickListener{
            print("!11")
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        return root


    }

    fun init(){



    }

}