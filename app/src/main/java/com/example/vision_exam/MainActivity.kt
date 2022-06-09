package com.example.vision_exam

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.vision_exam.kotlin.CameraXLivePreviewActivity
import com.example.vision_exam.kotlin.CameraXSourceDemoActivity
import com.example.vision_exam.kotlin.LivePreviewActivity
import com.example.vision_exam.kotlin.StillImageActivity
import com.example.vision_exam.kotlin.posedetector.PoseGraphic
import com.github.mikephil.charting.utils.Utils.init
import com.squareup.okhttp.internal.Internal.instance
import org.w3c.dom.Text
import java.util.*

/** Demo app chooser which allows you pick from all available testing Activities. */


class MainActivity :

    AppCompatActivity(),ActivityCompat.OnRequestPermissionsResultCallback, OnItemClickListener {


  lateinit var context:Context



  init {
context=this
  }
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_chooser)
        //tt
  init_tts2()

        speak("123")
        // Set up ListView and Adapter
        val listView = findViewById<ListView>(R.id.test_activity_list_view)
        val adapter = MyArrayAdapter(this, android.R.layout.simple_list_item_2, CLASSES)
        adapter.setDescriptionIds(DESCRIPTION_IDS)
        listView.adapter = adapter
        listView.onItemClickListener = this
        if (!allRuntimePermissionsGranted()) {
            getRuntimePermissions()
        }

        val button = findViewById<Button>(R.id.button5)

        button.setOnClickListener {
            speak("hello world")
        }

    }

   public fun init_tts2() {
       /*
       tts=TextToSpeech(instance, TextToSpeech.OnInitListener {
           isTtsready = true
           tts!!.language = Locale.ENGLISH
           tts_active=true
       })
 */

       /*
       tts=TextToSpeech(this){
           if(it==TextToSpeech.SUCCESS)
           {
                   val result=tts?.setLanguage(Locale.ENGLISH)
               if(result==TextToSpeech.LANG_MISSING_DATA)
               {
                   Toast.makeText(context,"no 안됨",Toast.LENGTH_SHORT).show()
                   Log.d(TAG, "감사 안됨")
               }
               Toast.makeText(context,"성공스",Toast.LENGTH_SHORT).show()
               Log.d(TAG, "감사 성공")
           }
           else
           {
               Toast.makeText(context,"us없음",Toast.LENGTH_SHORT).show()
               Log.d(TAG, "감사 안됨")
           }
       }

        */

   }
    public fun speak(str:String){
        tts?.speak(str,TextToSpeech.QUEUE_ADD,null,null)
    }

    private fun allRuntimePermissionsGranted(): Boolean {
        for (permission in REQUIRED_RUNTIME_PERMISSIONS) {
            permission?.let {
                if (!isPermissionGranted(this, it)) {
                    return false
                }
            }
        }
        return true
    }

    private fun getRuntimePermissions() {
        val permissionsToRequest = ArrayList<String>()
        for (permission in REQUIRED_RUNTIME_PERMISSIONS) {
            permission?.let {
                if (!isPermissionGranted(this, it)) {
                    permissionsToRequest.add(permission)
                }
            }
        }

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                PERMISSION_REQUESTS
            )
        }
    }

    private fun isPermissionGranted(context: Context, permission: String): Boolean {
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.i(TAG, "Permission granted: $permission")
            return true
        }
        Log.i(TAG, "Permission NOT granted: $permission")
        return false
    }




    override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
        val clicked = CLASSES[position]
        print("-----")
        print(clicked)
        startActivity(Intent(this, clicked))
    }

    private class MyArrayAdapter(
        private val ctx: Context,
        resource: Int,
        private val classes: Array<Class<*>>
    ) : ArrayAdapter<Class<*>>(ctx, resource, classes) {
        private var descriptionIds: IntArray? = null

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var view = convertView

            if (convertView == null) {
                val inflater = ctx.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = inflater.inflate(android.R.layout.simple_list_item_2, null)
            }

            (view!!.findViewById<View>(android.R.id.text1) as TextView).text =
                classes[position].simpleName
            descriptionIds?.let {
                (view.findViewById<View>(android.R.id.text2) as TextView).setText(it[position])
            }

            return view
        }

        fun setDescriptionIds(descriptionIds: IntArray) {
            this.descriptionIds = descriptionIds
        }
    }

    companion object {
        private const val TAG = "ChooserActivity"
        public var tts:TextToSpeech?=null
        var isTtsready=false
        var tts_active=false
        var ttscouns=0
         var instance=MainActivity().context
        var s= arrayOf("one","two","three","four","five","six","seven","eight","nine","ten")
        private val CLASSES =
            if (VERSION.SDK_INT < VERSION_CODES.LOLLIPOP)
                arrayOf<Class<*>>(
                    LivePreviewActivity::class.java,
                    StillImageActivity::class.java,
                )
            else
                arrayOf<Class<*>>(
                    LivePreviewActivity::class.java,
                    StillImageActivity::class.java,
                    CameraXLivePreviewActivity::class.java,
                    CameraXSourceDemoActivity::class.java
                )
        private val DESCRIPTION_IDS =
            if (VERSION.SDK_INT < VERSION_CODES.LOLLIPOP)
                intArrayOf(
                    R.string.desc_camera_source_activity,
                    R.string.desc_still_image_activity,
                )
            else
                intArrayOf(
                    R.string.desc_camera_source_activity,
                    R.string.desc_still_image_activity,
                    R.string.desc_camerax_live_preview_activity,
                    R.string.desc_cameraxsource_demo_activity
                )

        public const val PERMISSION_REQUESTS = 1

        private val REQUIRED_RUNTIME_PERMISSIONS =
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )

        public fun start_tts(str:String){
            speak(str)
        }
        public fun init_tts() {
                tts=TextToSpeech(instance, TextToSpeech.OnInitListener {
                    isTtsready = true
                    tts!!.language = Locale.US
                    tts_active=true
                })

        }

        public fun speak(str:String){
            if(str!="") {

                tts?.speak(s[str.toInt()-1], TextToSpeech.QUEUE_ADD, null, null)
                tts?.playSilentUtterance(1000,TextToSpeech.QUEUE_ADD,null)
                Log.d("", "${s[str.toInt()]} 중간인사람")
            }
        }




    public fun speakto(str:String){
        if(str!="") {
            tts?.speak(str, TextToSpeech.QUEUE_ADD, null, null)
            //tts?.speak(s[str.toInt()], TextToSpeech.QUEUE_ADD, null, null)
            tts?.playSilentUtterance(1000,TextToSpeech.QUEUE_ADD,null)

        }
    }
    }




    override fun onStop() {
        super.onStop()
        tts!!.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        tts!!.shutdown()
    }
}

