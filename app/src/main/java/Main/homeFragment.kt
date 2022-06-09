package Main


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vision_exam.R


class homeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val nume1=arguments?.getInt("num")
        Log.d("", "${nume1} 종강하고싶은사람")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}