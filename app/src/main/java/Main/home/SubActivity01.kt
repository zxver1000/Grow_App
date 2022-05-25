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
        when{
            shape=="비만"-> {
                shape_i.setText("비만")
                shape_p.setImageResource(R.drawable.shape_ex01)
                shape_e.setText("테이블 버피")
                shape_ei.setText("허리 높이 정도 테이블 앞에 서서 양 손으로 테이블 짚어서 다리를 한쪽씩 뻗어준다. 다리를 한쪽씩 다시 가져오고 팔을 하늘 위로 쭈욱- 만세를 한다.")
            }
            shape=="과체중"-> {
                shape_i.setText("과체중")
                shape_p.setImageResource(R.drawable.shape_ex02)
                shape_e.setText("서서 다리 당기기")
                shape_ei.setText("오른쪽 발을 뒤쪽으로 뻗고 두 팔은 모아서 위로 뻗어준다. 복근에 힘을 주면서 왼쪽 발에 무게 중심을 싣고 오른쪽 무릎을 구부려서 가슴 방향으로 당겨오며, 손을 내리고 상체를 오른쪽 방향으로 틀어준다.")
            }
            shape=="표준"-> {
                shape_i.setText("표준")
                shape_p.setImageResource(R.drawable.shape_ex03)
                shape_e.setText("바이시클 운동")
                shape_ei.setText("무릎을 가슴쪽으로 당긴 상태에서 똑바로 누워 손은 머리 뒤에 두고, 복근에 크게 힘을 준다. 왼쪽 다리를 바깥쪽으로 쭉 펴면서 상체를 오른쪽으로 틀어주며, 왼쪽 팔꿈치를 오른쪽 무릎으로 가져간다.")
            }
            shape=="저체중"-> {
                shape_i.setText("저체중")
                shape_p.setImageResource(R.drawable.shape_ex04)
                shape_e.setText("스쿼트")
                shape_ei.setText("두 발이 일직선 상이 되도록 하고 발을 어깨 넓이로 벌리고 무릎은 발끝을 나오지 않게 앉는다. 발 뒤꿈치에 힘을 주고 엉덩이로 밀어 올린다는 느낌으로 일어난다.")
            }

        }

        //레벨 조건문
        //사진, 운동 이름, 설명 수정 필요
        //다음 버튼 만들 예정
        //내용은 각 항목별 3개씩
        when{
            level==1-> {
                level_i.setText("Level.1")
                level_p.setImageResource(R.drawable.levelex01)
                level_e.setText("스탠딩 니업")
                level_ei.setText("양발을 어깨너비로 서서 한 손은 허리 옆에, 한 손은 머리 위로 올리고 사진과 같이 왼손에 오른발, 오른손에 왼발로 진행한다. 한쪽 먼저 15회 해주고 바꿔서 15회를 해준다."
                        )
            }
            //레벨2 수정필요
            level==2-> {
                level_i.setText("Level.2")
                level_p.setImageResource(R.drawable.ex06crunch)
                level_e.setText("크런치")
                level_ei.setText("바닥에 누워 무릎을 구부리고 발이 바닥과 떨어지지 않도록 한다. 양손을 귀에 대고 복부에 힘을 주면서 고개를 살짝 들고 등을 둥글게 구부리면서 상복부를 수축했다가 천천히 몸통을 바닥으로 눕힌다.")
            }
            level==3-> {
                level_i.setText("Level.3")
                level_p.setImageResource(R.drawable.levelex03)
                level_e.setText("암 워킹")
                level_ei.setText("다리는 정렬 혹은 골반 너비로 벌리고 선다. 다리는 최대한 굽히지 않도록 하며 손으로 바닥을 짚는다. 다리를 펴면서 앞으로 간다. 최대한 끝까지 가면 양 손을 나란히 짚고 플랭크 자세를 취한다. 처음 자세로 다시 돌아온다.")
            }

        }

        //운동기구 조건문
        //사진, 운동 이름, 설명 수정 필요
        //내용은 각 항목별 1개씩

        when{
            equip=="허벅지"-> {
                equip_i.setText("허벅지 운동")
                equip_p.setImageResource(R.drawable.equip01)
                equip_e.setText("매트, 폼롤러")
                equip_ei.setText("오른다리는 기역(ㄱ)자로 바닥에 고정하고 왼다리는 뒤로 보내 까치발을 유지한다. 왼다리의 뒤꿈치를 바닥으로 최대한 끌어 내려 5초 정도 유지한다.")
            }
            equip=="배"-> {
                equip_i.setText("배 운동")
                equip_p.setImageResource(R.drawable.equip02)
                equip_e.setText("매트, 공")
                equip_ei.setText("매트 위에 앉아 공을 무릎 사이에 끼운 뒤 두 팔을 나란히 뻗고 골반을 배꼽 방향으로 말아 낸 뒤 엉덩이의 무개중심을 꼬리뼈 뒤로 이동시킨다. 무릎 사이에 공을 조이며 10초 버틴다.")
            }
            equip=="옆구리"-> {
                equip_i.setText("옆구리 운동")
                equip_p.setImageResource(R.drawable.equip03)
                equip_e.setText("매트, 수건")
                equip_ei.setText("무릎을 접어 내려놓고 어깨너비로 수건을 잡아 만세를 만들어 옆구리를 접는다. 수건을 팽팽하게 잡아 정수리 위로 뽑아 내고 10초 정도 유지한다.")
            }
            equip=="팔"-> {
                equip_i.setText("팔 운동")
                equip_p.setImageResource(R.drawable.equip04)
                equip_e.setText("덤벨")
                equip_ei.setText("앉아 다리는 골반 너비만큼 벌린 후 덤벨을 잡고, 손바닥을 몸쪽으로 향하게 한다. 팔꿈치를 옆구리에 고정시키고, 덤벨을 이두근의 힘으로 들어올렸다가 천천히 덤벨을 내린다.")
            }
            equip=="등"-> {
                equip_i.setText("등 운동")
                equip_p.setImageResource(R.drawable.equip05)
                equip_e.setText("덤벨")
                equip_ei.setText("손등이 앞을 향하게 덤벨을 잡고, 양발을 어깨너비만큼 벌리고 선다. 무릎을 약간 굽히고 등을 아치형으로 만든 후 45도 정도 상체를 숙인다. 손바닥이 서로 마주 보도록 하며 하복부 쪽으로 덤벨을 잡아당기고 천천히 덤벨을 내려 처음 자세로 돌아온다.")
            }
            equip=="종아리"-> {
                equip_i.setText("종아리 운동")
                equip_p.setImageResource(R.drawable.equip06)
                equip_e.setText("요가링")
                equip_ei.setText("요가링을 종아리에 끼운다. 한쪽 다리를 앞으로 내밀고 무릎을 구부린다. 뒷다리는 무릎이 다 펴져 있어야 하며 뒤꿈치를 최대한 바닥에 밀착하려고 노력한다.")
            }

        }

    }
}
