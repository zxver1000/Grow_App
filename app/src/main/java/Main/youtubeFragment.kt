package Main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.vision_exam.R
import com.example.vision_exam.YoutubeItemFragment
import com.example.vision_exam.YoutubeViewFragment


class youtubeFragment : Fragment() {

    val youtubeItemFragment= YoutubeItemFragment()
    val youtubeViewFragment= YoutubeViewFragment()
    private var savedstate=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment=childFragmentManager.beginTransaction()
        fragment.replace(R.id.yt_fragment,YoutubeItemFragment())
        fragment.commit()
        return inflater.inflate(R.layout.fragment_youtube, container, false)
    }


    fun ytview(videoId:String){
        savedstate=1
        val bundle=Bundle().apply { putString("video",videoId) }
        youtubeViewFragment.apply { arguments=bundle }
        childFragmentManager.beginTransaction()
            .replace(R.id.yt_fragment, youtubeViewFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}