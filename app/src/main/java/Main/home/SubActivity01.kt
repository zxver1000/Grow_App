package Main.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.vision_exam.R
import org.w3c.dom.Text

class SubActivity01 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub01)
        init()
    }

    fun init(){
        var shape_i = findViewById<TextView>(R.id.shape_shape)
        val shape_p = findViewById<ImageView>(R.id.shape_img)
        var shape_e = findViewById<TextView>(R.id.shape_name)
        var shape_ei = findViewById<TextView>(R.id.shape_info)

        var level_i = findViewById<TextView>(R.id.level_shape)
        val level_p = findViewById<ImageView>(R.id.level_img)
        var level_e = findViewById<TextView>(R.id.level_name)
        var level_ei = findViewById<TextView>(R.id.level_info)

        var equip_i = findViewById<TextView>(R.id.equip_shape)
        val equip_p = findViewById<ImageView>(R.id.equip_img)
        var equip_e = findViewById<TextView>(R.id.equip_name)
        var equip_ei = findViewById<TextView>(R.id.equip_info)



        val shape:String = "표준" //설정할 때 체형 선택, 수정 필요
        val level:Int= 1 //설정할 때 레벨 필요
        val equip:String="허벅지" //설정할 때 부위 필요

        //체형 조건문
        //사진, 운동 이름, 설명 수정 필요
        //다음 버튼 만들 예정
        //내용은 각 항목별 3개씩
        when{
            shape=="비만"-> {
                shape_i.setText("비만")
                shape_p.setImageResource(R.drawable.dumbbell)
                shape_e.setText("비만 운동 이름")
                shape_ei.setText("비만 운동 설명")
            }
            shape=="과체중"-> {
                shape_i.setText("과체중")
                shape_p.setImageResource(R.drawable.dumbbell)
                shape_e.setText("과체중 운동 이름")
                shape_ei.setText("과체중 운동 설명")
            }
            shape=="표준"-> {
                shape_i.setText("표준")
                shape_p.setImageResource(R.drawable.dumbbell)
                shape_e.setText("표준 운동 이름")
                shape_ei.setText("표준 운동 설명")
            }
            shape=="저체중"-> {
                shape_i.setText("저체중")
                shape_p.setImageResource(R.drawable.dumbbell)
                shape_e.setText("저체중 운동 이름")
                shape_ei.setText("저체중 운동 설명")
            }

        }

        //레벨 조건문
        //사진, 운동 이름, 설명 수정 필요
        //다음 버튼 만들 예정
        //내용은 각 항목별 3개씩
        when{
            level==1-> {
                level_i.setText("1")
                level_p.setImageResource(R.drawable.dumbbell)
                level_e.setText("레벨1 운동 이름")
                level_ei.setText("레벨1 운동 설명")
            }
            level==2-> {
                level_i.setText("2")
                level_p.setImageResource(R.drawable.dumbbell)
                level_e.setText("레벨2 운동 이름")
                level_ei.setText("레벨2 운동 설명")
            }
            level==3-> {
                level_i.setText("3")
                level_p.setImageResource(R.drawable.dumbbell)
                level_e.setText("레벨3 운동 이름")
                level_ei.setText("레벨3 운동 설명")
            }

        }

        //운동기구 조건문
        //사진, 운동 이름, 설명 수정 필요
        //내용은 각 항목별 1개씩

        when{
            equip=="허벅지"-> {
                equip_i.setText("허벅지 운동 기구")
                level_p.setImageResource(R.drawable.dumbbell)
                level_e.setText("허벅지 운동 이름")
                level_ei.setText("허벅지 운동 설명")
            }
            equip=="배"-> {
                equip_i.setText("배 운동 기구")
                equip_p.setImageResource(R.drawable.dumbbell)
                equip_e.setText("배 운동 이름")
                equip_ei.setText("배 운동 설명")
            }
            equip=="옆구리"-> {
                equip_i.setText("옆구리 운동 기구")
                equip_p.setImageResource(R.drawable.dumbbell)
                equip_e.setText("옆구리 운동 이름")
                equip_ei.setText("옆구리 운동 설명")
            }
            equip=="팔"-> {
                equip_i.setText("팔 운동 기구")
                equip_p.setImageResource(R.drawable.dumbbell)
                equip_e.setText("팔 운동 이름")
                equip_ei.setText("팔 운동 설명")
            }
            equip=="등"-> {
                equip_i.setText("등 운동 기구")
                equip_p.setImageResource(R.drawable.dumbbell)
                equip_e.setText("등 운동 이름")
                equip_ei.setText("등 운동 설명")
            }
            equip=="종아리"-> {
                equip_i.setText("종아리 운동 기구")
                equip_p.setImageResource(R.drawable.dumbbell)
                equip_e.setText("종아리 운동 이름")
                equip_ei.setText("종아리 운동 설명")
            }

        }

    }
}
