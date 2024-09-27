package com.example.mydoc.Appointments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydoc.databinding.AppointmentItemBinding

class AppointmentAdapter(private var appointments: List<Appointment>) :
    RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    inner class AppointmentViewHolder(val binding: AppointmentItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val binding = AppointmentItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AppointmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointments[position]

        // Bind data to the UI
        holder.binding.doctorName.text = appointment.doctorName
        holder.binding.doctorSpecialization.text = appointment.specialization
        // Load doctor image (can use libraries like Glide or Picasso)
        holder.binding.doctorImage.setImageResource(appointment.imageResId)

        holder.binding.addReviewButton.setOnClickListener {
            // Handle add review click
        }
    }

    override fun getItemCount(): Int = appointments.size

    fun updateAppointments(newAppointments: List<Appointment>) {
        this.appointments = newAppointments
        notifyDataSetChanged()
    }
}
