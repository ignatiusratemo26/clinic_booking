package com.example.mydoc.network


import com.example.mydoc.Appointments.Appointment
import com.example.mydoc.Doctors.Doctor
import com.example.mydoc.Reviews.Review
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Path

interface ApiService {

    // Get list of doctors
    @GET("/doctors")
    suspend fun getDoctors(): Response<List<Doctor>>

    // Get doctor details by ID
    @GET("/doctors/{id}")
    suspend fun getDoctorDetails(@Path("id") doctorId: String): Response<Doctor>

    // Get appointments for a user
    @GET("/appointments/{userId}")
    suspend fun getAppointments(@Path("userId") userId: String): Response<List<Appointment>>

    // Create a new appointment
    @POST("/appointments")
    suspend fun createAppointment(@Body appointment: Appointment): Response<Appointment>

    // Add a review for an appointment
    @POST("/reviews")
    suspend fun addReview(@Body review: Review): Response<Review>

    // Get reviews for a doctor
    @GET("/reviews/{doctorId}")
    suspend fun getReviews(@Path("doctorId") doctorId: String): Response<List<Review>>
}
