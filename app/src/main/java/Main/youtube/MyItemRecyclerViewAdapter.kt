package com.example.vision_exam

import Main.youtube.youtubeplayerActivity
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.vision_exam.MyItemRecyclerViewAdapter.ViewHolder
import com.google.firebase.firestore.FirebaseFirestore

class MyItemRecyclerViewAdapter(
    private val values: ArrayList<YoutubeContent>
) : RecyclerView.Adapter<ViewHolder>() {

    val firebaseStore = FirebaseFirestore.getInstance()

    interface OnItemClickListener{
        fun OnItemClick(data: YoutubeContent, position: Int)
    }

    var itemClickListener:OnItemClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.fragment_youtube_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.youtube_exercise.text=item.content
        holder.youtube_exercise.setOnClickListener {
            val intent= Intent(holder.itemView?.context, youtubeplayerActivity::class.java)
            intent.putExtra("video",item)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            ContextCompat.startActivity(holder.itemView.context, intent,null)
        }
    }


    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val youtube_exercise: Button =view.findViewById(R.id.youtube_exercise)
    }

}