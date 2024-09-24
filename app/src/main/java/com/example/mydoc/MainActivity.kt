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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        // RecyclerView setup
        val doctorsList = listOf(
            Doctor("Dr. Hamza Tariq", "Senior Surgeon", "10:30 AM - 3:30 PM", 4.9, "$12"),
            Doctor("Dr. Alina Fatima", "Senior Surgeon", "10:30 AM - 3:30 PM", 5.0, "$12"),
            Doctor("Dr. Hamza Tariq", "Senior Surgeon", "10:30 AM - 3:30 PM", 4.9, "$12"),
            Doctor("Dr. Alina Fatima", "Senior Surgeon", "10:30 AM - 3:30 PM", 5.0, "$12"),
            Doctor("Dr. Hamza Tariq", "Senior Surgeon", "10:30 AM - 3:30 PM", 4.9, "$12"),
            Doctor("Dr. Alina Fatima", "Senior Surgeon", "10:30 AM - 3:30 PM", 5.0, "$12")
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        // Set up RecyclerView for top doctors
        val recyclerView: RecyclerView = findViewById(R.id.doctor_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DoctorAdapter(doctorsList)

        // Set up bottom navigation
//        val bottomNavigationView: BottomNavigationView = findViewById(R.id.mobile_navigation)
//        bottomNavigationView.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.navigation_home -> {
//                    // Handle Home action
//                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
//                    true
//                }
////                change to nav calendar
//                R.id.navigation_dashboard -> {
//                    // Handle Calendar action
//                    Toast.makeText(this, "Calendar", Toast.LENGTH_SHORT).show()
//                    true
//                }
////                change to nav_profile
//                R.id.navigation_notifications -> {
//                    // Handle Profile action
//                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
//                    true
//                }
//                else -> false
//            }
//        }





    }
}