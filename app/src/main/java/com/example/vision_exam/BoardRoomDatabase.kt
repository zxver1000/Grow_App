package com.example.vision_exam

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CalendarInfo::class), version = 1, exportSchema = false)
abstract class BoardRoomDatabase: RoomDatabase() {

    abstract fun boardDao(): BoardDAO

    companion object{
        @Volatile
        private var instance: BoardRoomDatabase? = null
        fun getDatabase(context: Context): BoardRoomDatabase{
            return instance?: synchronized(this){
                instance = Room.databaseBuilder(
                    context,
                    BoardRoomDatabase::class.java,
                    "board_database"
                ).build()
                instance!!
            }
        }
    }
}