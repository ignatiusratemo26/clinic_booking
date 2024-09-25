package com.example.mydoc

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mydoc.databinding.ActivityMainBinding

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var doctorAdapter: DoctorAdapter
    private lateinit var doctorsList: List<Doctor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_appointment, R.id.navigation_notifications
            )
        )


        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Set up RecyclerView for top doctors
        recyclerView = findViewById(R.id.doctor_list)  // Assuming you have a RecyclerView with this id in your layout
        recyclerView.layoutManager = LinearLayoutManager(this)


        doctorsList = getDoctorsList()  // Replace with your method to get the list of doctors
        doctorAdapter = DoctorAdapter(this, doctorsList)  // Ensure you pass the context and the list
        recyclerView.adapter = doctorAdapter



    }
    private fun getDoctorsList(): List<Doctor> {
        // Here, you'd typically fetch your data from a database or an API
        return listOf(
            Doctor("Dr. Ochieng Oloo", "Surgeon", "10:30 AM - 3:30 PM", 4.9, "Ksh 1500", R.drawable.ochieng),
            Doctor("Dr. Gwen Amanda", "Physiologist", "10:30 AM - 3:30 PM", 5.0, "Ksh 1200", R.drawable.gwen),
            Doctor("Dr. Mjumbe Carlton", "Dentist", "10:30 AM - 3:30 PM", 4.5, "Ksh 1200", R.drawable.mjumbe),
            Doctor("Dr. Fedha Santana", "Orthopedic", "10:30 AM - 3:30 PM", 5.0, "Ksh 1700" , R.drawable.fedha),
            Doctor("Dr. Ratemo Ernest", "Senior Surgeon", "10:30 AM - 3:30 PM", 4.4, "Ksh 1000", R.drawable.ratemo),
            Doctor("Dr. Alina Abdi", "Nutritionist", "10:30 AM - 3:30 PM", 5.0, "Ksh 1300", R.drawable.alina)
        )
    }
}