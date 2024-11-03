package Users

import java.util.Date

data class User(
    val id: Int,
    val firstName: String,
    val secondName: String,
    val email: String,
    val password: String,
    val dateOfBirth: Date,
    val gender: String,
    val phoneNumber: String,
    val address: String,
    val profilePicture: String,
    val createdAt: Date,
)
