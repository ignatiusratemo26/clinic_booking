package com.example.mydoc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mydoc.databinding.ActivityDoctorDetailBinding

class DoctorDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoctorDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_doctor_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding = ActivityDoctorDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Data from Intent
        val doctorName = intent.getStringExtra("DOCTOR_NAME")
        val doctorSpecialization = intent.getStringExtra("DOCTOR_SPECIALIZATION")
        val doctorTiming = intent.getStringExtra("DOCTOR_TIMING")
        val doctorRating = intent.getFloatExtra("DOCTOR_RATING", 0.0f)
        val doctorFee = intent.getStringExtra("DOCTOR_FEE")

//        seting the data to TextViews
        binding.doctorImage.setImageResource(R.drawable.ic_profile_placeholder)
        binding.doctorName.text = doctorName
        binding.doctorSpecialization.text = doctorSpecialization
//        binding.doctorTiming.text = doctorTiming
        binding.doctorRating.text = doctorRating.toString()
//        binding.doctorFee.text = doctorFee


    }
}