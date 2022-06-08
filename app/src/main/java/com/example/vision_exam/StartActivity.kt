package com.example.vision_exam

import Main.*
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.vision_exam.kotlin.CameraXLivePreviewActivity
import com.example.vision_exam.kotlin.CameraXSourceDemoActivity
import com.example.vision_exam.kotlin.LivePreviewActivity
import com.example.vision_exam.kotlin.StillImageActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

class d
{
    private var name=2;
    constructor(name:Int)
    {
        this.name=name
    }
}

class StartActivity : AppCompatActivity() {


    private val fl: FrameLayout by lazy{
        findViewById(R.id.fl_container)
    }
    private val bn: BottomNavigationView by lazy{
        findViewById(R.id.bnv_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        /*
        var store= FirebaseFirestore.getInstance()

        store.collection("종강").get().addOnSuccessListener {

                task->
            for (doc in task)
            {
                Log.d("", "${doc} 끝")
            }

        }
        val user = hashMapOf(
            "first" to "Alan",
            "middle" to "Mathison",
            "last" to "Turing",
            "born" to 1912
        )
        store.collection("이름").add(user)*/


        if (!allRuntimePermissionsGranted()) {
            getRuntimePermissions()
        }

        supportFragmentManager.beginTransaction().add(fl.id, homeFragment()).commit()
        bn.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.first->{
                    supportFragmentManager.beginTransaction().replace(fl.id, homeFragment()).commit()
                    true
                }
                R.id.second->{
                    supportFragmentManager.beginTransaction().replace(fl.id, badgeFragment()).commit()
                    true
                }
                R.id.third->{
                    supportFragmentManager.beginTransaction().replace(fl.id, youtubeFragment()).commit()
                    true

                }
                R.id.fourth->{
                    supportFragmentManager.beginTransaction().replace(fl.id, boardFragment()).commit()
                    true
                }

                else -> {
                    supportFragmentManager.beginTransaction().replace(fl.id, poseFragment()).commit()
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