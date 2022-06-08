package Main.youtube

import Main.signup.MyApplication
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.vision_exam.R
import com.example.vision_exam.YoutubeContent
import com.google.firebase.firestore.FirebaseFirestore
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class youtubeplayerActivity : AppCompatActivity() {
    private lateinit var youTubePlayerView: YouTubePlayerView
    var firebaseStore = FirebaseFirestore.getInstance() //firebase 연동
    val fbpath = firebaseStore.collection("회원정보").document(MyApplication.prefs.myEditText.toString())
    var watchNum = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtubeplayer)


        youTubePlayerView=findViewById(R.id.yt_view)
        val video=intent.getSerializableExtra("video") as YoutubeContent
        val videoId=video.videoId
        lifecycle.addObserver(youTubePlayerView)

        Log.e("load","$videoId")


        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                if(videoId.equals(0)) Log.d("youtube","videoId load error")
                else youTubePlayer.loadOrCueVideo(lifecycle,videoId,0f)
            }
        })

        fbpath.addSnapshotListener { snapshot, e ->
            if(snapshot != null) {
                watchNum = snapshot.data!!["youtubeWatchNum"].toString().toInt()
            }
        }

        if(watchNum==3) {
            Toast.makeText(this,"새로운 뱃지를 획득하였습니다 ! \n 마이페이지에서 뱃지를 확인해주세요 !",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            youTubePlayerView.enterFullScreen();
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            youTubePlayerView.exitFullScreen();
        }
    }
}