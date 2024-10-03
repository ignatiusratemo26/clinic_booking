package com.example.mydoc

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.mydoc.models.Doctors.Doctor
import com.example.mydoc.databinding.ActivityDoctorInputBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class DoctorInputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoctorInputBinding
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var specializationEditText: EditText
    private lateinit var hospitalIdEditText: EditText
    private lateinit var feeEditText: EditText
    private lateinit var ratingEditText: EditText
    private lateinit var bioEditText: EditText
    private lateinit var contactNumberEditText: EditText
    private lateinit var profileImageView: ImageView
    private lateinit var pickImageButton: Button
    private lateinit var submitButton: Button
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private var imageUri: Uri? = null

    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorInputBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.doctor_input)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        initializing firebase database and storage
        databaseReference = FirebaseDatabase.getInstance().getReference("doctors")
        storageReference = FirebaseStorage.getInstance().getReference("doctor_images")

//        initializing views
        firstNameEditText = binding.firstName
        lastNameEditText = binding.lastName
        specializationEditText = binding.specialization
        hospitalIdEditText = binding.hospitalId
        feeEditText = binding.fee
        ratingEditText = binding.rating
        bioEditText = binding.bio
        contactNumberEditText = binding.contactNumber
        profileImageView = binding.profileImage
        pickImageButton = binding.pickImageButton
        submitButton = binding.submitButton

//        registering the activity result launcher
        pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                imageUri = result.data?.data
                Glide.with(this).load(imageUri).into(profileImageView)
            }

        }
        pickImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            pickImageLauncher.launch(intent)

            }

        submitButton.setOnClickListener{
            addDoctorToFirebase()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data
            Glide.with(this).load(imageUri).into(profileImageView)
        }
    }
    companion object {
        private const val REQUEST_IMAGE_PICK = 1
    }

    private fun addDoctorToFirebase() {
        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        val specialization = specializationEditText.text.toString()
        val hospitalId = hospitalIdEditText.text.toString()
        val fee = feeEditText.text.toString()
        val rating = ratingEditText.text.toString().toFloat()
        val bio = bioEditText.text.toString()
        val contactNumber = contactNumberEditText.text.toString()

        if (firstName.isNotEmpty() && lastName.isNotEmpty() &&
            specialization.isNotEmpty() && hospitalId.isNotEmpty() &&
            fee.isNotEmpty() && rating > 0 && bio.isNotEmpty()
            && contactNumber.isNotEmpty() && imageUri != null) {

            val doctorId = databaseReference.push().key ?: ""
            val profileImageRef = storageReference.child("$doctorId.jpg")
            profileImageRef.putFile(imageUri!!)
                .addOnSuccessListener {
                    profileImageRef.downloadUrl.addOnSuccessListener { uri ->
                        val doctor = Doctor(
                            id = doctorId,
                            firstName = firstName,
                            lastName = lastName,
                            specializationId = specialization,
                            hospitalId = hospitalId,
                            profileImageUrl = uri.toString(),
                            fee = fee,
                            rating = rating,
                            bio = bio,
                            contactNumber = contactNumber
                        )
                        databaseReference.child(doctorId).setValue(doctor).addOnCompleteListener { task ->
                            if (task.isSuccessful){
                                Log.d("String", "Data added successfully")
                                Toast.makeText(this, "Added doctor successfully", Toast.LENGTH_SHORT).show()
                            } else {
                                Log.d("String", "Failed to add doctor")
                            }
                        }

                    }
                }
                .addOnFailureListener {
                    Log.d("DoctorInputActivity", "Failed to upload image")
                }

        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }

    }
}