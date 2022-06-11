package com.example.vision_exam

import Main.*
import Main.signup.MyApplication
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.vision_exam.kotlin.CameraXLivePreviewActivity
import com.example.vision_exam.kotlin.CameraXSourceDemoActivity
import com.example.vision_exam.kotlin.LivePreviewActivity
import com.example.vision_exam.kotlin.LivePreviewActivity.Companion.REQUIRED_RUNTIME_PERMISSIONS
import com.example.vision_exam.kotlin.StillImageActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.ArrayList


class StartActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    val today = LocalDate.now().toString()

    private val fl: FrameLayout by lazy{
        findViewById(R.id.fl_container)
    }
    private val bn: BottomNavigationView by lazy{
        findViewById(R.id.bnv_main)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

       val email = intent.getStringExtra("EMAIL4")
        if (email != null) {
            Log.d("TEST4",email)
        }
        val name = intent.getStringExtra("NAME4")!!
        val nickName = intent.getStringExtra("NICKNAME4")!!
        val bodypart = intent.getStringExtra("BODYPART4")!!
        val level = intent.getStringExtra("LEVEL4")!!
        val shape = intent.getStringExtra("SHAPE4")!!
        val firstAccessDay = intent.getStringExtra("FirstAccessDay4")!!

        var fragment2=homeFragment()
        var bundle = Bundle()

        if(firstAccessDay.equals(today)) {
            bundle.putBoolean("isOne",true)
        }

        bundle.putString("email",email)
        bundle.putString("name",name)
        bundle.putString("nickName",nickName)
        bundle.putString("bodypart",bodypart)
        bundle.putString("level",level)
        bundle.putString("shape",shape)
        bundle.putString("firstAccessDay5",firstAccessDay)
        fragment2.arguments=bundle

        supportFragmentManager.beginTransaction().add(fl.id,fragment2).commit()
        bn.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.first->{
                    supportFragmentManager.beginTransaction().replace(fl.id,fragment2).commit()
                    true
                }
                R.id.second->{
                    val bundle = Bundle()
                    bundle.putString("EMAIL4",email)
                    val youTubeFragment = youtubeFragment()
                    youTubeFragment.arguments = bundle
                    supportFragmentManager.beginTransaction().replace(fl.id, youTubeFragment).commit()
                    true
                }
                R.id.third->{

                    supportFragmentManager.beginTransaction().replace(fl.id, boardFragment()).commit()
                    true

                }
                R.id.fourth->{
                    supportFragmentManager.beginTransaction().replace(fl.id, poseFragment()).commit()
                    true
                }

                else -> {
                    val bundle = Bundle()
                    bundle.putString("EMAIL4",email)
                    val myPageFragment = mypageFragment()
                    myPageFragment.arguments = bundle
                    supportFragmentManager.beginTransaction().add(fl.id, myPageFragment).commit()
                    true
                }

            }


        }

    }

    private fun allRuntimePermissionsGranted(): Boolean {
        for (permission in StartActivity.REQUIRED_RUNTIME_PERMISSIONS) {
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
        for (permission in StartActivity.REQUIRED_RUNTIME_PERMISSIONS) {
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
                MainActivity.PERMISSION_REQUESTS
            )
        }
    }

    private fun isPermissionGranted(context: Context, permission: String): Boolean {
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.i(StartActivity.TAG, "Permission granted: $permission")
            return true
        }
        Log.i(StartActivity.TAG, "Permission NOT granted: $permission")
        return false
    }

    companion object {
        private const val TAG = "ChooserActivity"
        private val CLASSES =
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
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
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
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
    }


}