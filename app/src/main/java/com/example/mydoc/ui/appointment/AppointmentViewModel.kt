package com.example.mydoc.ui.appointment

import androidx.lifecycle.ViewModel
import com.example.mydoc.Appointments.Appointment
import com.example.mydoc.Doctors.Doctor
import com.example.mydoc.R

class AppointmentViewModel : ViewModel() {

    private val allAppointments = listOf(
        Appointment("Dr. Ochieng Oloo", "Surgeon",  R.drawable.ochieng),
        Appointment("Dr. Gwen Amanda", "Physiologist",  R.drawable.gwen),
        Appointment("Dr. Mjumbe Carlton", "Dentist",  R.drawable.mjumbe),
        Appointment("Dr. Fedha Santana", "Orthopedic", R.drawable.fedha),
        Appointment("Dr. Ratemo Ernest", "Senior Surgeon",  R.drawable.ratemo),
        Appointment("Dr. Alina Abdi", "Nutritionist",  R.drawable.alina)
    )

    fun getAppointments(status: String): List<Appointment> {
        // For simplicity, returning the same list for all statuses
        return allAppointments
    }
}
