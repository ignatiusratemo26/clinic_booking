package com.example.mydoc.Doctors

import androidx.lifecycle.*
import com.example.mydoc.network.Resource
import com.example.mydoc.repository.MyDocRepository
import kotlinx.coroutines.launch

class DoctorViewModel(private val repository: MyDocRepository) : ViewModel() {

    private val _doctors = MutableLiveData<Resource<List<Doctor>>>()
    val doctors: LiveData<Resource<List<Doctor>>> get() = _doctors

    fun fetchDoctors() {
        _doctors.postValue(Resource.Loading())
        viewModelScope.launch {
            _doctors.postValue(repository.getDoctors())
        }
    }

    private val _doctorDetails = MutableLiveData<Resource<Doctor>>()
    val doctorDetails: LiveData<Resource<Doctor>> get() = _doctorDetails

    fun fetchDoctorDetails(doctorId: String) {
        _doctorDetails.postValue(Resource.Loading())
        viewModelScope.launch {
            _doctorDetails.postValue(repository.getDoctorDetails(doctorId))
        }
    }
}
