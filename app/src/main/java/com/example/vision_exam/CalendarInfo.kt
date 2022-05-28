package com.example.vision_exam

import java.io.Serializable

data class CalendarInfo(
    val year: Int,
    val month: Int,
    val day: Int,
    val trainingProgress: String = "",
    val trainingDiary: String = ""
): Serializable
