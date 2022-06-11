package com.example.vision_exam

import Main.signup.MyApplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class BoardDetailActivity : AppCompatActivity() {

    val firebaseStore = FirebaseFirestore.getInstance()
    val fbpath =
        firebaseStore.collection("회원정보").document(MyApplication.prefs.myEditText.toString())
    var calRecordNum = 0

    companion object {
        const val TAG = "BoardDetailActivity"
    }

    private lateinit var curCalendarInfo: CalendarInfo
    private lateinit var db: BoardRoomDatabase
    private val saveBtn: Button by lazy {
        findViewById(R.id.save_btn)
    }
    private val taskContentEditText: EditText by lazy {
        findViewById(R.id.task_content)
    }
    private val diaryContentEditText: EditText by lazy {
        findViewById(R.id.diary_content)
    }
    private val time_content: TextView by lazy {
        findViewById(R.id.time_content)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_detail)
        db = BoardRoomDatabase.getDatabase(this)
        initIntent()

    }

    private fun initIntent() {
        curCalendarInfo = intent.getSerializableExtra("info") as CalendarInfo

        CoroutineScope(Dispatchers.Main).launch {
            var trainingInfo: List<CalendarInfo>
            withContext(Dispatchers.IO) {
                trainingInfo = db.boardDao().getTrainingInfo(
                    curCalendarInfo.year,
                    curCalendarInfo.month,
                    curCalendarInfo.day
                )
            }
            if (trainingInfo.isNotEmpty()) {
                Log.d(TAG, "initIntent: ${trainingInfo.toString()}")
                taskContentEditText.setText(trainingInfo.last().trainingProgress)
                diaryContentEditText.setText(trainingInfo.last().trainingDiary)
                time_content.setText("${trainingInfo.last().minute}분")
                curCalendarInfo.minute = trainingInfo.last().minute
            }
        }


        saveBtn.setOnClickListener {

                if (taskContentEditText.text.toString()
                        .isEmpty() || diaryContentEditText.text.toString().isEmpty()
                ) {
                    Toast.makeText(
                        this@BoardDetailActivity,
                        "Fill in the blanks~!!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {

                    fbpath.addSnapshotListener { snapshot, e ->
                        if (snapshot != null) {
                            calRecordNum = snapshot.data!!["calenderRecordNum"].toString().toInt()
                        }
                    }

                        calRecordNum += 1
                        firebaseStore.collection("회원정보")
                            .document(MyApplication.prefs.myEditText.toString())
                            .update("calenderRecordNum", calRecordNum)

                        curCalendarInfo.trainingDiary = diaryContentEditText.text.toString()
                    curCalendarInfo.trainingProgress = taskContentEditText.text.toString()
                    CoroutineScope(Dispatchers.IO).launch {
                        db.boardDao().insert(curCalendarInfo)
                    }
                    if (calRecordNum == 1 || calRecordNum == 3 || calRecordNum == 10) {
                        Toast.makeText(
                            this@BoardDetailActivity,
                            "새로운 뱃지를 획득하였습니다 ! \n 마이페이지에서 뱃지를 확인해주세요 !",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        Toast.makeText(this@BoardDetailActivity, "Saved~!!", Toast.LENGTH_SHORT)
                            .show()
                    }


                    finish()
                }
            }

            Log.e(TAG, "initIntent: ${curCalendarInfo}",)

        }
    }
