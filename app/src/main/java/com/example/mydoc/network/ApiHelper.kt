package com.example.mydoc.network
import com.example.mydoc.models.Appointments.Appointment
import com.example.mydoc.models.Doctors.Doctor
import com.example.mydoc.Reviews.Review
import com.example.mydoc.models.TimeSlots.TimeSlot
import retrofit2.Response

class ApiHelper(private val apiService: ApiService) {

    suspend fun getDoctors(): Response<List<Doctor>> = apiService.getDoctors()

    suspend fun getDoctorDetails(doctorId: String): Response<Doctor> = apiService.getDoctorDetails(doctorId)

    suspend fun getAppointments(userId: String): Response<List<Appointment>> = apiService.getAppointments(userId)

    suspend fun createAppointment(appointment: Appointment): Response<Appointment> = apiService.createAppointment(appointment)

    suspend fun getAvailableTimeSlots(doctorId: String): Response<List<TimeSlot>> = apiService.getAvailableTimeSlots(doctorId)

    suspend fun addReview(review: Review): Response<Review> = apiService.addReview(review)

    suspend fun getReviews(doctorId: String): Response<List<Review>> = apiService.getReviews(doctorId)
}
