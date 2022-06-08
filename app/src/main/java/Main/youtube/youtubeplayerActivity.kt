package Main.youtube

import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.vision_exam.*
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
class youtubeplayerActivity : AppCompatActivity() {
    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var db: BoardRoomDatabase
    private lateinit var curCalendarInfo: CalendarInfo
    private var starttime=0L
    private var endtime=0L
    private var interval=0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtubeplayer)

        db =  BoardRoomDatabase.getDatabase(this)

        youTubePlayerView=findViewById(R.id.yt_view)
        val video=intent.getSerializableExtra("video") as YoutubeContent
        val videoId=video.videoId
        lifecycle.addObserver(youTubePlayerView)
        gettoday()

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                starttime= SystemClock.elapsedRealtime()
                if(videoId.equals(0)) Log.d("youtube","videoId load error")
                else youTubePlayer.loadOrCueVideo(lifecycle,videoId,0f)
            }
        })
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

    override fun onStop() {
        super.onStop()
        updatetime()
    }


    private fun updatetime(){
        endtime=SystemClock.elapsedRealtime()
        interval=endtime-starttime
        var minute=(interval/1000/60).toInt()
        Log.d("check","${minute}")

        curCalendarInfo.minute+=minute

        CoroutineScope(Dispatchers.IO).launch {
            db.boardDao().insert(curCalendarInfo)
            Log.d("check","${curCalendarInfo.minute}")
        }
    }

    private fun gettoday(){
        val current = LocalDateTime.now()
        val yearformatter = DateTimeFormatter.ofPattern("yyyy")
        val monthformatter=DateTimeFormatter.ofPattern("MM")
        val dayformatter=DateTimeFormatter.ofPattern("dd")
        var year = current.format(yearformatter).toInt()
        var month=current.format(monthformatter).toInt()
        var day=current.format(dayformatter).toInt()

        curCalendarInfo = CalendarInfo(year, month, day)

        CoroutineScope(Dispatchers.Main).launch {
            var trainingInfo: List<CalendarInfo>
            withContext(Dispatchers.IO){
                trainingInfo = db.boardDao().getTrainingInfo(curCalendarInfo.year,curCalendarInfo.month,curCalendarInfo.day)
            }
            if (trainingInfo.isNotEmpty()){
                curCalendarInfo.minute=trainingInfo.last().minute
                curCalendarInfo.trainingProgress=trainingInfo.last().trainingProgress
                curCalendarInfo.trainingDiary=trainingInfo.last().trainingDiary
                Log.d("check","${curCalendarInfo.minute}")
            }
        }
    }
}