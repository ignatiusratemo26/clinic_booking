package Users

import java.util.Date

data class User(
    val id: Int,
    val first_name: String,
    val second_name: String,
    val email: String,
    val password: String,
    val date_of_birth: Date,
    val gender: String,
    val phone_number: String,
    val address: String,
    val profile_picture: String,
    val created_at: Date,
)
