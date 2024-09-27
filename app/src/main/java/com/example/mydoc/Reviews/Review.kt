package com.example.mydoc.Reviews

data class Review(
    val appointmentId: String,
    val doctorId: String,
    val rating: Float,
    val comment: String
)