package com.example.mydoc.ui.appointment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mydoc.databinding.FragmentAppointmentBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydoc.Appointments.AppointmentAdapter

class AppointmentFragment : Fragment() {

    private var _binding: FragmentAppointmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var appointmentAdapter: AppointmentAdapter
    private lateinit var viewModel: AppointmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(AppointmentViewModel::class.java)

        _binding = FragmentAppointmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize RecyclerView
        appointmentAdapter = AppointmentAdapter(listOf())  // Empty list initially
        binding.appointmentsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.appointmentsRecyclerview.adapter = appointmentAdapter

        // Load data initially (e.g., complete appointments)
        loadAppointments("Complete")

        // Handle tab clicks
        binding.tabComplete.setOnClickListener {
            loadAppointments("Complete")
        }
        binding.tabUpcoming.setOnClickListener {
            loadAppointments("Upcoming")
        }
        binding.tabCancelled.setOnClickListener {
            loadAppointments("Cancelled")
        }

        return root
    }

    private fun loadAppointments(status: String) {
        // Fetch the filtered appointment list from ViewModel based on status
        val appointments = viewModel.getAppointments(status)
        appointmentAdapter.updateAppointments(appointments)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
