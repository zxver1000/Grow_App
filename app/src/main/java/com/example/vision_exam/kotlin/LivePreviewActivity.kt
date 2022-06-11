/*
 * Copyright 2020 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.vision_exam.kotlin
import Main.*
import Main.resultPose.ResultActivity
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.vision_exam.*
import com.google.android.gms.common.annotation.KeepName
import com.google.mlkit.common.model.LocalModel
import com.example.vision_exam.kotlin.barcodescanner.BarcodeScannerProcessor
import com.example.vision_exam.kotlin.facedetector.FaceDetectorProcessor
import com.example.vision_exam.kotlin.labeldetector.LabelDetectorProcessor
import com.example.vision_exam.kotlin.objectdetector.ObjectDetectorProcessor
import com.example.vision_exam.kotlin.posedetector.PoseDetectorProcessor
import com.example.vision_exam.kotlin.segmenter.SegmenterProcessor
import com.example.vision_exam.kotlin.textdetector.TextRecognitionProcessor
import com.example.vision_exam.preference.PreferenceUtils
import com.example.vision_exam.preference.SettingsActivity
import com.example.vision_exam.preference.SettingsActivity.LaunchSource
import com.google.mlkit.vision.label.custom.CustomImageLabelerOptions
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.google.mlkit.vision.text.chinese.ChineseTextRecognizerOptions
import com.google.mlkit.vision.text.devanagari.DevanagariTextRecognizerOptions
import com.google.mlkit.vision.text.japanese.JapaneseTextRecognizerOptions
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.IOException
import com.example.vision_exam.kotlin.posedetector.PoseGraphic
import java.util.*
import kotlin.properties.Delegates

/** Live preview demo for ML Kit APIs. */
@KeepName
class LivePreviewActivity :
  AppCompatActivity(), OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {

  private var cameraSource: CameraSource? = null
  private var preview: CameraSourcePreview? = null
  private var graphicOverlay: GraphicOverlay? = null
  private var selectedModel = POSE_DETECTION
  private  lateinit var button:Button
  private lateinit var  button2:Button
  var sk=0
  override fun onCreate(savedInstanceState: Bundle?) {


    super.onCreate(savedInstanceState)

    val isOne = intent.getBooleanExtra("isOne",false)
    val isFive = intent.getBooleanExtra("isFive",false)
    val isTen = intent.getBooleanExtra("isTen",false)

    if (isOne || isFive || isTen){
      Toast.makeText(this@LivePreviewActivity, "새로운 뱃지를 획득하였습니다 ! \n 마이페이지에서 뱃지를 확인해주세요 !", Toast.LENGTH_SHORT).show()
    }
    Log.d(TAG, "onCreate")
    PoseGraphic.z_array.clear()
    setContentView(R.layout.activity_vision_live_preview)
    preview = findViewById(R.id.preview_view)
    if (preview == null) {
      Log.d(TAG, "Preview is null")
    }

    graphicOverlay = findViewById(R.id.graphic_overlay)
    if (graphicOverlay == null) {
      Log.d(TAG, "graphicOverlay is null")
    }

    val spinner = findViewById<Spinner>(R.id.spinner)
    val options: MutableList<String> = ArrayList()
    options.add(POSE_DETECTION)
    options.add(SELFIE_SEGMENTATION)
    options.add(TEXT_RECOGNITION_LATIN)
    options.add(TEXT_RECOGNITION_CHINESE)
    options.add(TEXT_RECOGNITION_DEVANAGARI)
    options.add(TEXT_RECOGNITION_JAPANESE)
    options.add(TEXT_RECOGNITION_KOREAN)
    options.add(OBJECT_DETECTION)
    options.add(OBJECT_DETECTION_CUSTOM)
    options.add(CUSTOM_AUTOML_OBJECT_DETECTION)
    options.add(FACE_DETECTION)
    options.add(BARCODE_SCANNING)
    options.add(IMAGE_LABELING)
    options.add(IMAGE_LABELING_CUSTOM)
    options.add(CUSTOM_AUTOML_LABELING)

    // Creating adapter for spinner
    val dataAdapter = ArrayAdapter(this, R.layout.spinner_style, options)

    // Drop down layout style - list view with radio button
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    // attaching data adapter to spinner
    spinner.adapter = dataAdapter
    spinner.onItemSelectedListener = this

    val facingSwitch = findViewById<ToggleButton>(R.id.facing_switch)
    facingSwitch.setOnCheckedChangeListener(this)

    val settingsButton = findViewById<ImageView>(R.id.settings_button)
    settingsButton.setOnClickListener {
      val intent = Intent(applicationContext, SettingsActivity::class.java)
      intent.putExtra(SettingsActivity.EXTRA_LAUNCH_SOURCE, LaunchSource.LIVE_PREVIEW)
      startActivity(intent)
    }

    val button = findViewById<Button>(R.id.stop_btn2)
    button.setOnClickListener {
      val intent = Intent(this, ResultActivity::class.java)
      startActivity(intent)
      finish()
    }

    val button4 = findViewById<Button>(R.id.button5)
    button4.setOnClickListener {
      Log.d("","helloworld")
      speakto("hello world")
    }

    createCameraSource(selectedModel)
    init_tts2()

  }

  public fun init_tts2() {
    /*
    tts=TextToSpeech(instance, TextToSpeech.OnInitListener {
        isTtsready = true
        tts!!.language = Locale.ENGLISH
        tts_active=true
    })
*/

    tts = TextToSpeech(this, TextToSpeech.OnInitListener {
    tts!!.language = Locale.US
    })

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



fun start_graph(){
  val intent = Intent(this, ResultActivity::class.java)
  startActivity(intent)
  finish()
}
  fun btn_set(){
    button=findViewById(R.id.stop_button)
    button2=findViewById(R.id.stop_btn2)
    button2?.setOnClickListener {
      val intent = Intent(this, ResultActivity::class.java)
      startActivity(intent)

    }
    button?.setOnClickListener {

      val intent = Intent(this, ResultActivity::class.java)
      startActivity(intent)
    }
  }


  private fun allRuntimePermissionsGranted(): Boolean {
    for (permission in LivePreviewActivity.REQUIRED_RUNTIME_PERMISSIONS) {
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
    for (permission in LivePreviewActivity.REQUIRED_RUNTIME_PERMISSIONS) {
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
      Log.i(LivePreviewActivity.TAG, "Permission granted: $permission")
      return true
    }
    Log.i(LivePreviewActivity.TAG, "Permission NOT granted: $permission")
    return false
  }






  @Synchronized
  override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
    // An item was selected. You can retrieve the selected item using
    // parent.getItemAtPosition(pos)
    selectedModel = parent?.getItemAtPosition(pos).toString()
    Log.d(TAG, "Selected model: $selectedModel")
    preview?.stop()
    createCameraSource(selectedModel)
    startCameraSource()
  }

  override fun onNothingSelected(parent: AdapterView<*>?) {
    // Do nothing.
  }

  override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
    Log.d(TAG, "Set facing")
    if (cameraSource != null) {
      if (isChecked) {
        cameraSource?.setFacing(CameraSource.CAMERA_FACING_FRONT)
      } else {
        cameraSource?.setFacing(CameraSource.CAMERA_FACING_BACK)
      }
    }
    preview?.stop()
    startCameraSource()
  }

  private fun createCameraSource(model: String) {
    // If there's no existing cameraSource, create one.
    if (cameraSource == null) {
      cameraSource = CameraSource(this, graphicOverlay)
    }
    try {
      when (model) {
        POSE_DETECTION -> {
          val poseDetectorOptions = PreferenceUtils.getPoseDetectorOptionsForLivePreview(this)
          Log.i(TAG, "Using Pose Detector with options $poseDetectorOptions")
          val shouldShowInFrameLikelihood =
            PreferenceUtils.shouldShowPoseDetectionInFrameLikelihoodLivePreview(this)
          val visualizeZ = PreferenceUtils.shouldPoseDetectionVisualizeZ(this)
          val rescaleZ = PreferenceUtils.shouldPoseDetectionRescaleZForVisualization(this)
          val runClassification = true
          cameraSource!!.setMachineLearningFrameProcessor(
            PoseDetectorProcessor(
              this,
              poseDetectorOptions,
              shouldShowInFrameLikelihood,
              visualizeZ,
              rescaleZ,
              runClassification,
              /* isStreamMode = */ true
            )
          )
        }
        OBJECT_DETECTION_CUSTOM -> {
          Log.i(TAG, "Using Custom Object Detector Processor")
          val localModel =
            LocalModel.Builder().setAssetFilePath("custom_models/object_labeler.tflite").build()
          val customObjectDetectorOptions =
            PreferenceUtils.getCustomObjectDetectorOptionsForLivePreview(this, localModel)
          cameraSource!!.setMachineLearningFrameProcessor(
            ObjectDetectorProcessor(this, customObjectDetectorOptions)
          )
        }
        CUSTOM_AUTOML_OBJECT_DETECTION -> {
          Log.i(TAG, "Using Custom AutoML Object Detector Processor")
          val customAutoMLODTLocalModel =
            LocalModel.Builder().setAssetManifestFilePath("automl/manifest.json").build()
          val customAutoMLODTOptions =
            PreferenceUtils.getCustomObjectDetectorOptionsForLivePreview(
              this,
              customAutoMLODTLocalModel
            )
          cameraSource!!.setMachineLearningFrameProcessor(
            ObjectDetectorProcessor(this, customAutoMLODTOptions)
          )
        }
        TEXT_RECOGNITION_LATIN -> {
          Log.i(TAG, "Using on-device Text recognition Processor for Latin and Latin")
          cameraSource!!.setMachineLearningFrameProcessor(
            TextRecognitionProcessor(this, TextRecognizerOptions.Builder().build())
          )
        }
        TEXT_RECOGNITION_CHINESE -> {
          Log.i(TAG, "Using on-device Text recognition Processor for Latin and Chinese")
          cameraSource!!.setMachineLearningFrameProcessor(
            TextRecognitionProcessor(this, ChineseTextRecognizerOptions.Builder().build())
          )
        }
        TEXT_RECOGNITION_DEVANAGARI -> {
          Log.i(TAG, "Using on-device Text recognition Processor for Latin and Devanagari")
          cameraSource!!.setMachineLearningFrameProcessor(
            TextRecognitionProcessor(this, DevanagariTextRecognizerOptions.Builder().build())
          )
        }
        TEXT_RECOGNITION_JAPANESE -> {
          Log.i(TAG, "Using on-device Text recognition Processor for Latin and Japanese")
          cameraSource!!.setMachineLearningFrameProcessor(
            TextRecognitionProcessor(this, JapaneseTextRecognizerOptions.Builder().build())
          )
        }
        TEXT_RECOGNITION_KOREAN -> {
          Log.i(TAG, "Using on-device Text recognition Processor for Latin and Korean")
          cameraSource!!.setMachineLearningFrameProcessor(
            TextRecognitionProcessor(this, KoreanTextRecognizerOptions.Builder().build())
          )
        }
        FACE_DETECTION -> {
          Log.i(TAG, "Using Face Detector Processor")
          val faceDetectorOptions = PreferenceUtils.getFaceDetectorOptions(this)
          cameraSource!!.setMachineLearningFrameProcessor(
            FaceDetectorProcessor(this, faceDetectorOptions)
          )
        }
        BARCODE_SCANNING -> {
          Log.i(TAG, "Using Barcode Detector Processor")
          cameraSource!!.setMachineLearningFrameProcessor(BarcodeScannerProcessor(this))
        }
        IMAGE_LABELING -> {
          Log.i(TAG, "Using Image Label Detector Processor")
          cameraSource!!.setMachineLearningFrameProcessor(
            LabelDetectorProcessor(this, ImageLabelerOptions.DEFAULT_OPTIONS)
          )
        }
        IMAGE_LABELING_CUSTOM -> {
          Log.i(TAG, "Using Custom Image Label Detector Processor")
          val localClassifier =
            LocalModel.Builder().setAssetFilePath("custom_models/bird_classifier.tflite").build()
          val customImageLabelerOptions = CustomImageLabelerOptions.Builder(localClassifier).build()
          cameraSource!!.setMachineLearningFrameProcessor(
            LabelDetectorProcessor(this, customImageLabelerOptions)
          )
        }
        CUSTOM_AUTOML_LABELING -> {
          Log.i(TAG, "Using Custom AutoML Image Label Detector Processor")
          val customAutoMLLabelLocalModel =
            LocalModel.Builder().setAssetManifestFilePath("automl/manifest.json").build()
          val customAutoMLLabelOptions =
            CustomImageLabelerOptions.Builder(customAutoMLLabelLocalModel)
              .setConfidenceThreshold(0f)
              .build()
          cameraSource!!.setMachineLearningFrameProcessor(
            LabelDetectorProcessor(this, customAutoMLLabelOptions)
          )
        }

        OBJECT_DETECTION -> {
          Log.i(TAG, "Using Object Detector Processor")
          val objectDetectorOptions = PreferenceUtils.getObjectDetectorOptionsForLivePreview(this)
          cameraSource!!.setMachineLearningFrameProcessor(
            ObjectDetectorProcessor(this, objectDetectorOptions)
          )
        }
        SELFIE_SEGMENTATION -> {
          cameraSource!!.setMachineLearningFrameProcessor(SegmenterProcessor(this))
        }
        else -> Log.e(TAG, "Unknown model: $model")
      }
    } catch (e: Exception) {
      Log.e(TAG, "Can not create image processor: $model", e)
      Toast.makeText(
          applicationContext,
          "Can not create image processor: " + e.message,
          Toast.LENGTH_LONG
        )
        .show()
    }
  }

  /**
   * Starts or restarts the camera source, if it exists. If the camera source doesn't exist yet
   * (e.g., because onResume was called before the camera source was created), this will be called
   * again when the camera source is created.
   */
  private fun startCameraSource() {
    if (cameraSource != null) {
      try {
        if (preview == null) {
          Log.d(TAG, "resume: Preview is null")
        }
        if (graphicOverlay == null) {
          Log.d(TAG, "resume: graphOverlay is null")
        }
        preview!!.start(cameraSource, graphicOverlay)
      } catch (e: IOException) {
        Log.e(TAG, "Unable to start camera source.", e)
        cameraSource!!.release()
        cameraSource = null
      }
    }
  }

  public override fun onResume() {
    super.onResume()
    Log.d(TAG, "onResume")
    createCameraSource(selectedModel)
    startCameraSource()
  }

  /** Stops the camera. */
  override fun onPause() {
    super.onPause()
    preview?.stop()
  }

  public override fun onDestroy() {
    super.onDestroy()
    if (cameraSource != null) {
      cameraSource?.release()
    }
  }

  companion object {
    private const val POSE_DETECTION = "KLAP"
    private const val OBJECT_DETECTION = "Object Detection"
    private const val OBJECT_DETECTION_CUSTOM = "Custom Object Detection"
    private const val CUSTOM_AUTOML_OBJECT_DETECTION = "Custom AutoML Object Detection (Flower)"
    private const val FACE_DETECTION = "Face Detection"
    private const val TEXT_RECOGNITION_LATIN = "Text Recognition Latin"
    private const val TEXT_RECOGNITION_CHINESE = "Text Recognition Chinese"
    private const val TEXT_RECOGNITION_DEVANAGARI = "Text Recognition Devanagari"
    private const val TEXT_RECOGNITION_JAPANESE = "Text Recognition Japanese"
    private const val TEXT_RECOGNITION_KOREAN = "Text Recognition Korean"
    private const val BARCODE_SCANNING = "Barcode Scanning"
    private const val IMAGE_LABELING = "Image Labeling"
    private const val IMAGE_LABELING_CUSTOM = "Custom Image Labeling (Birds)"
    private const val CUSTOM_AUTOML_LABELING = "Custom AutoML Image Labeling (Flower)"

    private const val SELFIE_SEGMENTATION = "Selfie Segmentation"

    private const val TAG = "LivePreviewActivity"

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

    public val PERMISSION_REQUESTS = 1

    public val REQUIRED_RUNTIME_PERMISSIONS =
      arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
      )


    public var tts:TextToSpeech?=null
    var ttscouns=0
    var s= arrayOf("one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen")
    public fun speak(str:String){
      if(str!="") {

        tts?.speak(s[str.toInt()-1], TextToSpeech.QUEUE_ADD, null, null)
        tts?.playSilentUtterance(1000, TextToSpeech.QUEUE_ADD,null)
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



    var something:Int by Delegates.observable(1){props,old,new->


      Log.d("", "${old} 올드")
      Log.d("", "${new} 올드 뉴뉴")

   if(new==5)
   {

   }

    }
  }




}

