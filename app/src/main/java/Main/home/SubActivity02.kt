package Main.home

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.vision_exam.R

class SubActivity02 : AppCompatActivity() {

    lateinit var adapter: ArrayAdapter<String>
    //arrayOf는 수정불가 mutable은 가능능
    val exercise = mutableListOf<String>( //정보 8개만 넣기
        "push-up", "buffy test", "sit-up", "jumping jacks", "crunch", "mountain climber", "leg scissors", "lunge"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub02)
        initLayout()
    }

    private fun initLayout(){
        val layout02=findViewById<LinearLayout>(R.id.layout02)
        layout02.isVisible=false
        val autoCompleteTextView=findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

        adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, exercise)
        //현재 context 정보, 리소스-안드로이드의 레이아웃, 데이터
        autoCompleteTextView.setAdapter(adapter) //autocompletetextview에 연결해줌
        autoCompleteTextView.setOnItemClickListener{adapterView, view, i, l ->
            //i 포지션 정보
            val item = adapterView.getItemAtPosition(i).toString()
            Toast.makeText(this, "Finished Searching", Toast.LENGTH_SHORT).show()
        }

        val button = findViewById<Button>(R.id.button)
        //text왓쳐는 인터페이스로 인터페이스를 구현한 객체가 들어가야 함
        //글자를 입력하면 입력에 따른 이벤트 처리

        autoCompleteTextView.addTextChangedListener{
            val str = it.toString()
            button.isEnabled=str.isNotEmpty()
        }


        button.setOnClickListener {
            layout02.isVisible=true
            val eximage = findViewById<ImageView>(R.id.eximage)
            val exname = autoCompleteTextView.text.toString()
            val exnameblank=findViewById<TextView>(R.id.exname)
            val exkcal=findViewById<TextView>(R.id.exkcal)
            val exdetail=findViewById<TextView>(R.id.exdetail)

            //adapter.add(editText.text.toString())//country에 직접 넣는게 아니라 리스트를 관리하는 어댑터에
            //그러면 어댑터에 연결되어있는 리스트에 자동으로 추가됨
            //adapter.notifyDataSetChanged() //이걸 꼭 호출해야 갱신이 됨
            when{
                exname=="push-up"->{
                    exnameblank.setText("push-up")
                    exkcal.setText("42 kcal per 10 minutes")
                    exdetail.setText("Representative muscle exercise\n\n" +
                            "In general, it is an exercise that can be done without using a device, and the body is raised by the force of stretching both arms by focusing the whole body's weight on four points of two hands and two toes while lying down. The basic method is to bend the movement and elbow joint to lower the body to the point where it does not stick to the ground, and repeat it. ")
                    eximage.setImageResource(R.drawable.ex01)
                }
                exname=="lunge"->{
                    exnameblank.setText("lunge")
                    exkcal.setText("60 kcal per 10 minutes")
                    exdetail.setText("Exercise that strengthens the lower body muscles and gives elasticity to the thighs and hips\n" +
                            "Spread your feet pelvis width apart, put your hands on your waist, and stand right away. Spread your right foot forward about 70-100cm, and raise the heel of your left foot. With your back and waist straight, bend your right knee 90 degrees, and your left knee feels like touching the floor, lower your body, and slowly return to your first position using the strength of your lower body")
                    eximage.setImageResource(R.drawable.ex02lunge)
                }
                exname=="jumping jacks"->{
                    exnameblank.setText("jumping jacks")
                    exkcal.setText("80 kcal per 10 minutes")
                    exdetail.setText("systemic aerobic exercise\n" +
                            "Stand at attention and jump while raising both arms to both sides. Return to the attention position and fold and spread your feet, and put your hands on your head and clap. Return to attention position.")
                    eximage.setImageResource(R.drawable.ex03jump)
                }
                exname=="buffy test"->{
                    exnameblank.setText("buffy test")
                    exkcal.setText("300 kcal per 10 minutes")
                    exdetail.setText("Start in a standing position.\n" +
                            "Bend in a squat position, hold the floor, stretch your legs back, lie down, and stretch your legs, pull your legs back, and return to the squat position and stand up.")
                    eximage.setImageResource(R.drawable.ex04buf)
                }
                exname=="sit-up"->{
                    exnameblank.setText("sit-up")
                    exkcal.setText("84 calories per 10 minutes")
                    exdetail.setText("It is an exercise in which both hands are locked behind the head while lying down, legs are fixed, the upper body is bent forward, and the elbow touches the knee and goes down again.\n" +
                            "It is effective in developing endurance for the entire body, including the waist.")

                    eximage.setImageResource(R.drawable.ex05situp)
                }
                exname=="crunch"->{
                    exnameblank.setText("crunch")
                    exkcal.setText("80 per 10 minutes kcal")
                    exdetail.setText("exercises to strengthen the upper part of the abdominal muscles\n\n" +
                            "Lie on the floor, bend your knees, and keep your feet from falling off the floor. Put both hands on the ears and apply force to the abdomen, slightly raise the head, and bend the back in a round shape to contract the upper abdomen. Slowly lay the torso on the floor, feeling the tension in the upper abdomen")
                    eximage.setImageResource(R.drawable.ex06crunch)
                }
                exname=="mountain climber"->{
                    exnameblank.setText("mountain climber")
                    exkcal.setText("100 kcal per 10 minutes")
                    exdetail.setText("Exercise that helps build muscle strength and balances the body\n" +
                            "Since it is an aerobic exercise and a muscle exercise, it increases the heart rate quickly to help strengthen cardiopulmonary endurance.")

                    eximage.setImageResource(R.drawable.ex07mountain)
                }
                exname=="leg scissors"->{
                    exnameblank.setText("leg scissors")
                    exkcal.setText("60 kcal per 10 minutes")
                    exdetail.setText("Exercise that strengthens the lower body muscles and gives elasticity to the thighs and hips\n" +
                            "Spread your feet pelvis width apart, put your hands on your waist, and stand right away. Spread your right foot forward about 70-100cm, and raise the heel of your left foot. With your back and waist straight, bend your right knee to 90 degrees, and your left knee lowers your body with the feeling of touching the floor, and slowly returns to your first position using the strength of your lower body.")

                    eximage.setImageResource(R.drawable.ex08legs)
                }
                else->{
                    exnameblank.setText("There is no information about the exercise")
                    exkcal.setText("")
                    exdetail.setText("")
                    eximage.setImageResource(R.drawable.else01)
                }

            }
            autoCompleteTextView.text.clear()
        }
    }
}