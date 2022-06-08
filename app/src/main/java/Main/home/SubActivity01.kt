package Main.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.vision_exam.R
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text

class SubActivity01 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub01)

        val subemail = intent.getStringExtra("email2")!!
        if (subemail != null) {
            Log.d("subactivity: ",subemail)
        }

        var level_i = findViewById<TextView>(R.id.level_shape)
        val level_p = findViewById<ImageView>(R.id.level_img)
        var level_e = findViewById<TextView>(R.id.level_name)
        var level_ei = findViewById<TextView>(R.id.level_info)

        var equip_i = findViewById<TextView>(R.id.equip_shape)
        val equip_p = findViewById<ImageView>(R.id.equip_img)
        var equip_e = findViewById<TextView>(R.id.equip_name)
        var equip_ei = findViewById<TextView>(R.id.equip_info)

        init()
        Log.d("","시작")
        var store= FirebaseFirestore.getInstance()

        store.collection("회원정보").document(subemail).get()
            .addOnSuccessListener {
                result->
                //체형별
                    //구현예정
                //레벨
                //Log.d("", "${doc.get("종강")} 끝")
                if(result["level"]=="Beginner"){
                    level_i.setText("Beginner")
                    level_p.setImageResource(R.drawable.levelex01)
                    level_e.setText("Standing knee-up")
                    level_ei.setText("Stand with both feet shoulder-width, one hand next to the waist, one hand over the head, and proceed with the right foot in the left hand and the left foot in the right hand as shown in the picture.")
                }else if(result["level"]=="Intermediate"){
                    level_i.setText("Intermediate")
                    level_p.setImageResource(R.drawable.ex06crunch)
                    level_e.setText("Crunch")
                    level_ei.setText("Lie on the floor, bend your knees, and keep your feet from falling off the floor. Put both hands on the ears and apply force to the abdomen, bend the back, contract the upper abdomen, and slowly lay the torso on the floor.")
                }else if(result["level"]=="Advanced"){
                    level_i.setText("Advanced")
                    level_p.setImageResource(R.drawable.levelex03)
                    level_e.setText("Arm walking")
                    level_ei.setText("Legs stand with pelvis width apart. Do not bend your legs and touch the floor with your hands. Stretch your legs and go forward. If you go as far as possible, hold both hands side by side and take a plank position.")
                }

                //기구
                if(result["bodypart"]=="thigh"){
                    equip_i.setText("thigh")
                    equip_p.setImageResource(R.drawable.equip01)
                    equip_e.setText("Mat, foam roller")
                    equip_ei.setText("Bend your right leg and fix it on the floor, and send your left leg back to maintain your tiptoe. Pull the heel of the one leg to the floor as much as possible and maintain it for about 5 seconds.")
                }else if(result["bodypart"]=="belly"){
                    equip_i.setText("belly")
                    equip_p.setImageResource(R.drawable.equip02)
                    equip_e.setText("Mat, ball")
                    equip_ei.setText("Sit on the mat, put the ball between your knees, stretch out your arms, roll out your pelvis, and move the center of the buttocks to the back of the tailbone. Hold for 10 seconds, tightening the ball between the knees.")
                }else if(result["bodypart"]=="side"){
                    equip_i.setText("side")
                    equip_p.setImageResource(R.drawable.equip03)
                    equip_e.setText("Mat, towel")
                    equip_ei.setText("Fold your knees down, grab a towel shoulder-width, make a hurrah, and fold your sides. Hold the towel tightly and pull it out over the top of the head and maintain it for about 10 seconds.")
                }else if(result["bodypart"]=="arm"){
                    equip_i.setText("arm")
                    equip_p.setImageResource(R.drawable.equip04)
                    equip_e.setText("dumbbell")
                    equip_ei.setText("Sit, spread your legs as wide as your pelvis, hold the dumbbell, and turn your palms toward your body. Fix the elbow to the side, lift the dumbbell with the strength of the biceps, and slowly lower the dumbbell.")
                }else if(result["bodypart"]=="back"){
                    equip_i.setText("back")
                    equip_p.setImageResource(R.drawable.equip05)
                    equip_e.setText("dumbbell")
                    equip_ei.setText("Hold the dumbbell and stand with your feet shoulder-width apart. Bend your knees and bend your upper body about 45 degrees. With the palms facing each other, pull the dumbbell toward the lower abdomen, lower the dumbbell, and return to the first position.")
                }else if(result["bodypart"]=="calf"){
                    equip_i.setText("calf")
                    equip_p.setImageResource(R.drawable.equip06)
                    equip_e.setText("Yoga ring")
                    equip_ei.setText("Place the yoga ring in your calves. Put one leg forward and bend the knee. Stretch your knees and try to keep your heels as close to the floor as possible.")
                }else if(result["bodypart"]=="shoulder"){
                    equip_i.setText("shoulder")
                    equip_p.setImageResource(R.drawable.equip07)
                    equip_e.setText("band")
                    equip_ei.setText("Spread your legs shoulder-width apart and hold the band with both feet and hands. Repeat the movement with both hands raised to shoulder level.")
                }else if(result["bodypart"]=="chest"){
                    equip_i.setText("chest")
                    equip_p.setImageResource(R.drawable.equip08)
                    equip_e.setText("press machine")
                    equip_ei.setText("Hold the handle so that it is next to the chest, and raise the handle up as you stretch your elbow. Stop for about 1 second and return to its original state.")
                }







        }
    }

    fun init(){
        var shape_i = findViewById<TextView>(R.id.shape_shape)
        val shape_p = findViewById<ImageView>(R.id.shape_img)
        var shape_e = findViewById<TextView>(R.id.shape_name)
        var shape_ei = findViewById<TextView>(R.id.shape_info)

        val shape:String = "표준" //설정할 때 체형 선택, 수정 필요


        //체형 조건문
        when{
            shape=="비만"-> {
                shape_i.setText("비만")
                shape_p.setImageResource(R.drawable.shape_ex01)
                shape_e.setText("Table Buffy")
                shape_ei.setText("Stand in front of the table about waist-high and stretch your legs one by one with both hands. Bring your legs back one at a time and raise your arms above the sky - hurrah.")
            }
            shape=="과체중"-> {
                shape_i.setText("과체중")
                shape_p.setImageResource(R.drawable.shape_ex02)
                shape_e.setText("Pulling legs while standing")
                shape_ei.setText("Extend your right foot to the back, gather your arms, and extend upward. While applying force, place the center of gravity on the left foot, bend the right knee, pull it in the chest direction, and twist the upper body in the right direction.")
            }
            shape=="표준"-> {
                shape_i.setText("표준")
                shape_p.setImageResource(R.drawable.shape_ex03)
                shape_e.setText("Bicycle movement")
                shape_ei.setText("Lie straight with your knees pulled toward your chest, put your hands behind your head, and put a lot of strength on your abs. Stretch your left leg outward and turn your upper body to the right, and take your left elbow to your right knee.")
            }
            shape=="저체중"-> {
                shape_i.setText("저체중")
                shape_p.setImageResource(R.drawable.shape_ex04)
                shape_e.setText("squats")
                shape_ei.setText("Make sure your feet are in a straight line, spread your feet shoulder width, and sit down so that your knees do not come out. It wakes up feeling like you're pushing up with your buttocks and putting strength on your heels.")
            }

        }

        //레벨 조건문
        //사진, 운동 이름, 설명 수정 필요
        //다음 버튼 만들 예정
        //내용은 각 항목별 3개씩
        /*when{
            level=="Beginner"-> {
                level_i.setText("Beginner")
                level_p.setImageResource(R.drawable.levelex01)
                level_e.setText("Standing knee-up")
                level_ei.setText("Stand with both feet shoulder-width, one hand next to the waist, one hand over the head, and proceed with the right foot in the left hand and the left foot in the right hand as shown in the picture.")
            }
            //레벨2 수정필요
            level=="Intermediate"-> {
                level_i.setText("Intermediate")
                level_p.setImageResource(R.drawable.ex06crunch)
                level_e.setText("Crunch")
                level_ei.setText("Lie on the floor, bend your knees, and keep your feet from falling off the floor. Put both hands on the ears and apply force to the abdomen, bend the back, contract the upper abdomen, and slowly lay the torso on the floor.\n")
            }
            level=="Advanced"-> {
                level_i.setText("Advanced")
                level_p.setImageResource(R.drawable.levelex03)
                level_e.setText("Arm walking")
                level_ei.setText("Legs stand with pelvis width apart. Do not bend your legs and touch the floor with your hands. Stretch your legs and go forward. If you go as far as possible, hold both hands side by side and take a plank position.")
            }

        }*/

        //운동기구 조건문
        //사진, 운동 이름, 설명 수정 필요
        //내용은 각 항목별 1개씩
/*
        when{
            equip=="thigh"-> {
                equip_i.setText("thigh")
                equip_p.setImageResource(R.drawable.equip01)
                equip_e.setText("Mat, foam roller")
                equip_ei.setText("Bend your right leg and fix it on the floor, and send your left leg back to maintain your tiptoe. Pull the heel of the one leg to the floor as much as possible and maintain it for about 5 seconds.")
            }
            equip=="belly"-> {
                equip_i.setText("belly")
                equip_p.setImageResource(R.drawable.equip02)
                equip_e.setText("Mat, ball")
                equip_ei.setText("Sit on the mat, put the ball between your knees, stretch out your arms, roll out your pelvis, and move the center of the buttocks to the back of the tailbone. Hold for 10 seconds, tightening the ball between the knees.")
            }
            equip=="side"-> {
                equip_i.setText("side")
                equip_p.setImageResource(R.drawable.equip03)
                equip_e.setText("Mat, towel")
                equip_ei.setText("Fold your knees down, grab a towel shoulder-width, make a hurrah, and fold your sides. Hold the towel tightly and pull it out over the top of the head and maintain it for about 10 seconds.")
            }
            equip=="arm"-> {
                equip_i.setText("arm")
                equip_p.setImageResource(R.drawable.equip04)
                equip_e.setText("dumbbell")
                equip_ei.setText("Sit, spread your legs as wide as your pelvis, hold the dumbbell, and turn your palms toward your body. Fix the elbow to the side, lift the dumbbell with the strength of the biceps, and slowly lower the dumbbell.")
            }
            equip=="back"-> {
                equip_i.setText("back")
                equip_p.setImageResource(R.drawable.equip05)
                equip_e.setText("dumbbell")
                equip_ei.setText("Hold the dumbbell and stand with your feet shoulder-width apart. Bend your knees and bend your upper body about 45 degrees. With the palms facing each other, pull the dumbbell toward the lower abdomen, lower the dumbbell, and return to the first position.")
            }
            equip=="calf"-> {
                equip_i.setText("calf")
                equip_p.setImageResource(R.drawable.equip06)
                equip_e.setText("Yoga ring")
                equip_ei.setText("Place the yoga ring in your calves. Put one leg forward and bend the knee. Stretch your knees and try to keep your heels as close to the floor as possible.")
            }
            equip=="shoulder"-> {
                equip_i.setText("shoulder")
                equip_p.setImageResource(R.drawable.equip07)
                equip_e.setText("band")
                equip_ei.setText("Spread your legs shoulder-width apart and hold the band with both feet and hands. Repeat the movement with both hands raised to shoulder level.")
            }
            equip=="chest"-> {
                equip_i.setText("chest")
                equip_p.setImageResource(R.drawable.equip08)
                equip_e.setText("press machine")
                equip_ei.setText("Hold the handle so that it is next to the chest, and raise the handle up as you stretch your elbow. Stop for about 1 second and return to its original state.")
            }

        }*/

    }
}
