package com.example.mydoc

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mydoc.databinding.ActivityMainBinding

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydoc.models.Doctors.Doctor
import com.example.mydoc.models.Doctors.DoctorAdapter
import com.example.mydoc.network.ApiClient
import com.example.mydoc.network.ApiHelper
import com.example.mydoc.network.Resource
import com.example.mydoc.repository.MyDocRepository
import com.google.firebase.database.*
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var doctorAdapter: DoctorAdapter
    private lateinit var doctorsList: MutableList<Doctor>
    private lateinit var doctorRepository: MyDocRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = FirebaseDatabase.getInstance()
        val doctorsRef = database.getReference("doctors")

        // Create sample doctors
        val doctor1 = Doctor(
            firstName = "Ochieng",
            lastName = "Oloo",
            specializationId = "1",
            hospitalId = "1",
            profileImageUrl = "url_to_image",
            rating = 4.9f,
            bio = "Surgeon",
            contactNumber = "1234567890"
        )

        // Add doctors to the database
        val newDoctorRef = doctorsRef.push()
        doctor1.id = newDoctorRef.key ?: ""
        newDoctorRef.child(doctor1.id).setValue(doctor1)

        val navView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_appointment,
                R.id.navigation_notifications,
                R.id.navigation_profile
            )
        )


        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Set up RecyclerView for top doctors
        recyclerView =
            findViewById(R.id.doctor_list)  // Assuming you have a RecyclerView with this id in your layout
        recyclerView.layoutManager = LinearLayoutManager(this)


        doctorsList = mutableListOf()  // Replace with your method to get the list of doctors
        doctorAdapter =
            DoctorAdapter(this, doctorsList)  // Ensure you pass the context and the list
        recyclerView.adapter = doctorAdapter

//        initialize repository
        doctorRepository = MyDocRepository(ApiHelper(ApiClient.apiService))
        fetchDoctors()
    }

    private fun fetchDoctors() {
        lifecycleScope.launch {
            try {
                val response = doctorRepository.getDoctors()
                if (response is Resource.Success && response.data != null) {
                    response.data.let { doctors ->
                        doctorsList.clear()
                        doctorsList.addAll(doctors)
                        doctorAdapter.notifyItemRangeInserted(0, doctors.size)
                    }
                } else {
                    Log.e("MainActivity", "Failed to fetch doctors: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Exception: ${e.message}")
            }
        }

    }
}