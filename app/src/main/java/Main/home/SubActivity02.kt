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
        "팔굽혀펴기", "버피테스트", "윗몸일으키기", "팔벌려뛰기", "크런치", "마운틴 클라이머", "레그시저스", "런지"
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
            Toast.makeText(this, "검색 완료", Toast.LENGTH_SHORT).show()
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
                exname=="팔굽혀펴기"->{
                    exnameblank.setText("팔굽혀펴기")
                    exkcal.setText("10분당 42kcal")
                    exdetail.setText("대표적인 근력 운동\n" +
                            "일반적으로 기구 등을 사용하지 않고 할 수 있는 운동으로, 엎드린 상태에서 전신의 체중을 두 손과 두 발가락의 4개소에 집중하여 양팔을 늘리는 힘에 의해 신체를 올린다. 동작과 팔꿈치 관절을 굽혀 몸을 지상에 붙지 않을 정도까지 낮추고, 반복하는 것이 기본적인 방법이다. ")
                    eximage.setImageResource(R.drawable.ex01)
                }
                exname=="런지"->{
                    exnameblank.setText("런지")
                    exkcal.setText("10분당 60kcal")
                    exdetail.setText("허벅지와 엉덩이에 탄력을 주며 하체 근력을 강화하는 운동\n" +
                            "두 발을 골반너비로 벌리고 허리에 손을 대고 바로 선다. 오른발을 앞으로 70~100cm 정도 벌려 내밀고, 왼발의 뒤꿈치를 세운다. 등과 허리를 똑바로 편 상태에서 오른쪽 무릎을 90도로 구부리고 왼쪽 무릎은 바닥에 닿는 느낌으로 몸을 내리고 하체의 힘을 이용해 천천히 처음 자세로 돌아온다")
                    eximage.setImageResource(R.drawable.ex02lunge)
                }
                exname=="팔벌려뛰기"->{
                    exnameblank.setText("팔벌려뛰기")
                    exkcal.setText("10분당 80kcal")
                    exdetail.setText("전신 유산소성 근력 운동\n" +
                            "차렷 자세를 취하고 두 팔을 양옆으로 올리면서 점프해 벌린다. 다시 차렷 자세로 돌아가고 두 발을 접프해서 벌리면서 두 손은 머리 위로 올려 박수를 친다. 다시 차렷 자세로 돌아간다.")
                    eximage.setImageResource(R.drawable.ex03jump)
                }
                exname=="버피테스트"->{
                    exnameblank.setText("버피테스트")
                    exkcal.setText("10분당 300kcal")
                    exdetail.setText("선 자세로 시작한다.\n" +
                            "스쿼트 자세로 몸을 굽혀 바닥을 짚고 다리를 뒤로 쭉 펴서 엎드려뻗친 자세를 취하고 다시 다리를 당겨서 스쿼트 자세로 돌아가서 일어선다.")
                    eximage.setImageResource(R.drawable.ex04buf)
                }
                exname=="윗몸일으키기"->{
                    exnameblank.setText("윗몸일으키기")
                    exkcal.setText("10분당 84kcal")
                    exdetail.setText("누운 상태에서 양 손을 머리뒤에 깍지를 낀 상태로 다리는 고정시키고 상체를 앞으로 굽혔다가 팔꿈치가 무릎을 닿고 다시 내려가는 것을 반복하는 운동이다.\n" +
                            "허리를 비롯해 신체 전반적인 지구력을 키우는 데 효과적이다.")

                    eximage.setImageResource(R.drawable.ex05situp)
                }
                exname=="크런치"->{
                    exnameblank.setText("크런치")
                    exkcal.setText("10분당 80kcal")
                    exdetail.setText("복직근 중 상부를 강화하는 운동\n" +
                            "바닥에 누워 무릎을 구부리고 발이 바닥과 떨어지지 않도록 한다. 양손을 귀에 대고 복부에 힘을 주면서 고개를 살짝 들고 등을 둥글게 구부리면서 상복부를 수축한다. 상복부의 긴장을 느끼면서 천천히 몸통을 바닥으로 눕힌다.")

                    eximage.setImageResource(R.drawable.ex06crunch)
                }
                exname=="마운틴 클라이머"->{
                    exnameblank.setText("마운틴 클라이머")
                    exkcal.setText("10분당 100kcal")
                    exdetail.setText("전신근력을 길러주며 신체의 균형을 잡아주는 운동\n" +
                            "유산소 운동인 동시에 근력 운동이기에 심박수를 빠르게 증가시켜 주어 심폐지구력 강화에 도움을 준다.\n" +
                            "체지방 제거 및 유연성 향상에 효과가 있다.")

                    eximage.setImageResource(R.drawable.ex07mountain)
                }
                exname=="레그시저스"->{
                    exnameblank.setText("레그시저스")
                    exkcal.setText("10분당 60kcal")
                    exdetail.setText("허벅지와 엉덩이에 탄력을 주며 하체 근력을 강화하는 운동\n" +
                            "두 발을 골반너비로 벌리고 허리에 손을 대고 바로 선다. 오른발을 앞으로 70~100cm 정도 벌려 내밀고, 왼발의 뒤꿈치를 세운다. 등과 허리를 똑바로 편 상태에서 오른쪽 무릎을 90도로 구부리고 왼쪽 무릎은 바닥에 닿는 느낌으로 몸을 내리고 하체의 힘을 이용해 천천히 처음 자세로 돌아온다.")

                    eximage.setImageResource(R.drawable.ex08legs)
                }
                else->{
                    exnameblank.setText("해당 운동에 대한 정보는 없습니다")
                    exkcal.setText("")
                    exdetail.setText("")
                    eximage.setImageResource(R.drawable.else01)
                }

            }
            autoCompleteTextView.text.clear()
        }
    }
}