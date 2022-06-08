package com.example.vision_exam

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "board_table", primaryKeys = ["year", "month", "day"])
data class CalendarInfo(
    val year: Int,
    val month: Int,
    val day: Int,
    var trainingProgress: String = "",
    var trainingDiary: String = "",
    var minute:Int=0
): Serializable
