package com.example.mydoc

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mydoc.databinding.ActivityAppointmentBookingBinding

class AppointmentBookingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppointmentBookingBinding
    private var selectedDay: TextView? = null
    private var selectedTime: TextView? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        enableEdgeToEdge()
        setContentView(R.layout.activity_appointment_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.booking)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        initializing view binding
        binding = ActivityAppointmentBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        doctor information
        val doctorImage = intent.getIntExtra("DOCTOR_IMAGE", R.drawable.ic_profile_placeholder)
        val doctorName = intent.getStringExtra("DOCTOR_NAME")
        val doctorSpecialization = intent.getStringExtra("DOCTOR_SPECIALIZATION")

        binding.doctorImage.setImageResource(doctorImage)
        binding.doctorName.text = doctorName
        binding.doctorSpecialization.text = doctorSpecialization

//        set up day selection
        setupDaySelection()

//        set up time slot selection
        setupTimeSelection()

//        handling confirm button click
        binding.confirmButton.setOnClickListener {
            //            implement booking logic here
        }

    }

    private fun setupDaySelection(){
        val days = listOf(binding.daySun, binding.dayMon, binding.dayTue, binding.dayWed, binding.dayThur, binding.dayFri)

        for (day in days) {
            day.setOnClickListener {
                selectedDay?.setBackgroundResource(R.drawable.day_selector_background)
                selectedDay = day
                day.setBackgroundResource(R.drawable.day_selector_selected)
            }
        }
    }
    private fun setupTimeSelection() {
        val times = listOf(binding.timeSlot9, binding.timeSlot930 /* Add other time slots */)

        for (time in times) {
            time.setOnClickListener {
                selectedTime?.setBackgroundResource(R.drawable.time_slot_unavailable)  // Reset previously selected time
                time.setBackgroundResource(R.drawable.time_slot_selected)  // Highlight the selected time
                selectedTime = time  // Update selected time
            }
        }
    }

}