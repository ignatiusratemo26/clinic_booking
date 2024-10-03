package com.example.mydoc.ui.appointment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydoc.models.Appointments.Appointment
import kotlinx.coroutines.launch
import com.example.mydoc.R
import com.example.mydoc.network.Resource

class AppointmentViewModel : ViewModel() {


    private val _appointments = MutableLiveData<Resource<List<Appointment>>>()
    val appointments: LiveData<Resource<List<Appointment>>> get() = _appointments

    fun fetchAppointments() {
        _appointments.postValue(Resource.Loading())
        viewModelScope.launch {
//            _appointments.postValue(repository.getAppointments())
        }
    }

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
