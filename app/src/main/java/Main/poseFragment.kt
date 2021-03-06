package Main

import Main.listview.ItemCard
import Main.listview.MyRecyclerView
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vision_exam.MainActivity
import com.example.vision_exam.R


class poseFragment : Fragment() {
    // TODO: Rename and change types of parameters

  lateinit var recylcerView:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root=inflater.inflate(R.layout.fragment_pose, container, false)
        // Inflate the layout for this fragment

        recylcerView=root.findViewById(R.id.recylcerView)
   init()
        return root
//dfddfdfd

    }

    fun init(){

        var items: ArrayList<ItemCard> = ArrayList()
        for (i in 0 until 10) {
            if(i%3==0)
            items.add(ItemCard(
                R.drawable.squat,
                R.drawable.ic_launcher_background,
                "SQUAT",
                "Add strength") )
            else if(i%3==1)
            {
                items.add(ItemCard(
                    R.drawable.push_up,
                    R.drawable.ic_launcher_background,
                    "PUSH_UP $i",
                    "Add strength") )
            }
            else if(i%3==2)
            {    items.add(ItemCard(
                R.drawable.pull_up,
                R.drawable.ic_launcher_background,
                "PULL_UP $i",
                "Add strength") )

            }
        }
        var adapter = MyRecyclerView(items)

        recylcerView.adapter = adapter
        recylcerView.layoutManager = LinearLayoutManager( context, RecyclerView.VERTICAL, false )



    }

}