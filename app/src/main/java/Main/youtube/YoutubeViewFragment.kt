package com.example.vision_exam

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class YoutubeViewFragment : Fragment() {
    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var callback:OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_youtube_view, container, false)
        youTubePlayerView=view.findViewById<YouTubePlayerView>(R.id.yt_view)

        initYouTubePlayerView()

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                /*if(youTubePlayerView.isFullScreen())
                    youTubePlayerView.exitFullScreen()
                else*/
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.yt_fragment,YoutubeItemFragment())
                    commit()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    /*
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            youTubePlayerView.enterFullScreen();
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            youTubePlayerView.exitFullScreen();
        }
    }*/

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

    fun initYouTubePlayerView(){
        val bundle = arguments
        val videoID=bundle!!.getString("video").toString()

        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadOrCueVideo(lifecycle,videoID,0f)
            }
        })
    }

}