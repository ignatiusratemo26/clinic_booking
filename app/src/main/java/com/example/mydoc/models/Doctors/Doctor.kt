package com.example.mydoc.models.Doctors

//data class Doctor(
//    val name: String,
//    val specialization: String,
//    val timing: String,
//    val rating: Double,
//    val fee: String,
//    val imageResId: Int
//)

data class Doctor(
    var id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val specializationId: String = "",
    val hospitalId: String = "",
    val profileImageUrl: String = "",
    val fee: String = "",
    val rating: Float = 0.0f,
    val bio: String = "",
    val contactNumber: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)