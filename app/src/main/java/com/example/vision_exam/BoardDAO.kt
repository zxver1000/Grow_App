package com.example.vision_exam

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import javax.inject.Qualifier

@Dao
interface BoardDAO {

    @Query("select * from board_table where year=:year and month=:month and day=:day")
    suspend fun getTrainingInfo(year: Int, month: Int, day:Int): List<CalendarInfo>

    @Insert
    suspend fun insert(calendarInfo: CalendarInfo)

    @Query("delete from board_table")
    suspend fun deleteAll()

}