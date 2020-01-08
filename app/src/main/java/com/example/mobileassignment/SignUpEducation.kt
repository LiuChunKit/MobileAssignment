package com.example.mobileassignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileassignment.models.Education
import com.example.mobileassignment.signup.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.sign_up_education.*

class SignUpEducation : AppCompatActivity() {

    private var mAuth = FirebaseAuth.getInstance()
    private var educationDatabase = FirebaseDatabase.getInstance().getReference("Education")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_education)

        mAuth = FirebaseAuth.getInstance()

        val confirmButton = findViewById<Button>(R.id.eduConfirmButton)
        val backButton = findViewById<Button>(R.id.eduConfirmButton)

        confirmButton.setOnClickListener() {
            saveNewEducation()
        }

        backButton.setOnClickListener() {
            backFunction()
        }

    }

    private fun saveNewEducation() {

        mAuth = FirebaseAuth.getInstance()

        val educationId = educationDatabase.push().key.toString()
        val schoolName = schoolNameText_edu.text.toString()
        val fieldOfStudy = fieldOfStudyText_edu.text.toString()
        val qualification = qualificationText_edu.text.toString()
        val graduateDate = graduateDateText_edu.text.toString()
        val userId = mAuth.currentUser

        val educationObject = Education (
            educationId,
            schoolName,
            fieldOfStudy,
            qualification,
            graduateDate,
            userId
        )

        educationDatabase.child(educationId).setValue(educationObject).addOnCompleteListener {
            startActivity(Intent(this, SignUpExperience::class.java))

        }
    }

    private fun backFunction() {
        startActivity(Intent(this, SignUpActivity::class.java))
    }
}