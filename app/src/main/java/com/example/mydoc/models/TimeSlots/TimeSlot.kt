package com.example.mydoc.models.TimeSlots

data class TimeSlot(
    val id: String,
    val doctorId: String,
    val startTime: String,
    val endTime: String
) {
    override fun toString(): String {
        return "$startTime - $endTime"
    }
}
