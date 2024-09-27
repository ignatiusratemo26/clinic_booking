package com.example.mydoc.repository

import com.example.mydoc.Appointments.Appointment
import com.example.mydoc.Doctors.Doctor
import com.example.mydoc.Reviews.Review
import com.example.mydoc.network.ApiHelper
import com.example.mydoc.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyDocRepository(private val apiHelper: ApiHelper) {

    suspend fun getDoctors(): Resource<List<Doctor>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiHelper.getDoctors()
                if (response.isSuccessful) {
                    Resource.Success(response.body() ?: listOf())
                } else {
                    Resource.Error("Failed to fetch doctors")
                }
            } catch (e: Exception) {
                Resource.Error("Network Error: ${e.message}")
            }
        }
    }

    suspend fun getDoctorDetails(doctorId: String): Resource<Doctor> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiHelper.getDoctorDetails(doctorId)
                if (response.isSuccessful) {
                    Resource.Success(response.body()!!)
                } else {
                    Resource.Error("Failed to fetch doctor details")
                }
            } catch (e: Exception) {
                Resource.Error("Network Error: ${e.message}")
            }
        }
    }

    suspend fun getAppointments(userId: String): Resource<List<Appointment>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiHelper.getAppointments(userId)
                if (response.isSuccessful) {
                    Resource.Success(response.body() ?: listOf())
                } else {
                    Resource.Error("Failed to fetch appointments")
                }
            } catch (e: Exception) {
                Resource.Error("Network Error: ${e.message}")
            }
        }
    }

    suspend fun createAppointment(appointment: Appointment): Resource<Appointment> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiHelper.createAppointment(appointment)
                if (response.isSuccessful) {
                    Resource.Success(response.body()!!)
                } else {
                    Resource.Error("Failed to create appointment")
                }
            } catch (e: Exception) {
                Resource.Error("Network Error: ${e.message}")
            }
        }
    }

    suspend fun addReview(review: Review): Resource<Review> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiHelper.addReview(review)
                if (response.isSuccessful) {
                    Resource.Success(response.body()!!)
                } else {
                    Resource.Error("Failed to add review")
                }
            } catch (e: Exception) {
                Resource.Error("Network Error: ${e.message}")
            }
        }
    }

    suspend fun getReviews(doctorId: String): Resource<List<Review>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiHelper.getReviews(doctorId)
                if (response.isSuccessful) {
                    Resource.Success(response.body() ?: listOf())
                } else {
                    Resource.Error("Failed to fetch reviews")
                }
            } catch (e: Exception) {
                Resource.Error("Network Error: ${e.message}")
            }
        }
    }
}
