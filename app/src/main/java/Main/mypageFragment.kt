package Main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.vision_exam.R


class mypageFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_mypage, container, false)
        val badge_button = root.findViewById<Button>(R.id.badge)

        badge_button.setOnClickListener {
            activity?.let {
                val intent = Intent(context, BadgeActivity::class.java)
                startActivity(intent)
            }
        }

        return root
    }


}