package Main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vision_exam.MyItemRecyclerViewAdapter
import com.example.vision_exam.R
import com.example.vision_exam.YoutubeContent


class youtubeFragment : Fragment() {

    private val yt_recyclerview: RecyclerView by lazy {
        view?.findViewById(R.id.yt_recyclerview) as RecyclerView
    }
    val exerciseList= arrayListOf<YoutubeContent>(
        YoutubeContent("beginner:\nfullbody","tzv5N3yikU4"),
        YoutubeContent("intermediate:\nfullbody","lKwZ2DU4P-A"),
        YoutubeContent("advanced:\nfullbody","wrLlzn5TjLY"),
        YoutubeContent("beginner:\nupperbody","54tTYO-vU2E"),
        YoutubeContent("intermediate:\nupperbody","GZtB7W9Uafk"),
        YoutubeContent("advanced:\nuppperbody","o-9ZuMtC8MA"),
        YoutubeContent("beginner:\nlowerbody","qEoa40A_aZY"),
        YoutubeContent("intermediate:\nlowerbody","pDFuLG0xrsU"),
        YoutubeContent("advanced:\nlowerbody","js8z5wIZ0wg"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_youtube, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        yt_recyclerview.setHasFixedSize(true)
        yt_recyclerview.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        val adapter= MyItemRecyclerViewAdapter(exerciseList)

        adapter.itemClickListener=object :MyItemRecyclerViewAdapter.OnItemClickListener{
            override fun OnItemClick(data: YoutubeContent, position: Int) {
                Toast.makeText(activity,"youtube click",Toast.LENGTH_SHORT).show()
                Log.v("toast","click listener")
            }
        }
        yt_recyclerview.adapter=adapter
    }
}