package Main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import com.example.vision_exam.BoardDetailActivity
import com.example.vision_exam.CalendarInfo
import com.example.vision_exam.R
import java.util.*


class boardFragment : Fragment() {


    val calNum= arguments?.getString("CALRECORDNUM").toString()
    val calendarView: CalendarView by lazy {
        requireView().findViewById(R.id.calendarView)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val c = Calendar.getInstance()
            //날짜 클릭
            val intent = Intent(requireActivity(), BoardDetailActivity::class.java)
            intent.putExtra("nowNum",calNum)
            val calendarInfo = CalendarInfo(year, month+1, dayOfMonth)
            intent.putExtra("info", calendarInfo)
            startActivity(intent)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}