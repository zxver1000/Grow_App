package Main.signup

import android.app.Application
import android.content.SharedPreferences
import com.example.vision_exam.preference.PreferenceUtils

class MyApplication : Application() {
    companion object {
        lateinit var  prefs: MySharedPreferences
    }

    override fun onCreate() {
        prefs = MySharedPreferences(applicationContext)
        super.onCreate()
    }
}