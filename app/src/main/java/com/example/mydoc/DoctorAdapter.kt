package com.example.mydoc

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoctorAdapter(private val context: Context,private val doctors: List<Doctor>) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.doctor_name)
        val specializationTextView: TextView = itemView.findViewById(R.id.doctor_title)
        val timingTextView: TextView = itemView.findViewById(R.id.doctor_schedule)
        val ratingTextView: TextView = itemView.findViewById(R.id.doctor_rating)
        val feeTextView: TextView = itemView.findViewById(R.id.doctor_fee)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.doctor_item, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctors[position]
        holder.nameTextView.text = doctor.name
        holder.specializationTextView.text = doctor.specialization
        holder.timingTextView.text = doctor.timing
        holder.ratingTextView.text = doctor.rating.toString()
        holder.feeTextView.text = doctor.fee

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DoctorDetailActivity::class.java).apply {
                putExtra("DOCTOR_NAME", doctor.name)
                putExtra("DOCTOR_SPECIALIZATION", doctor.specialization)
                putExtra("DOCTOR_TIMING", doctor.timing)
                putExtra("DOCTOR_RATING", doctor.rating)
                putExtra("DOCTOR_FEE", doctor.fee)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = doctors.size
}
