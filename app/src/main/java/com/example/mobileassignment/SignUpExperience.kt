package com.example.mobileassignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileassignment.models.Education
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.sign_up_education.*

class SignUpExperience : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_experience)

        val submitButton = findViewById<Button>(R.id.expSubmitButton)
        val backButton = findViewById<Button>(R.id.expBackButton)

        submitButton.setOnClickListener() {
            saveNewExperience()
        }

        backButton.setOnClickListener() {
            backFunction()
        }
    }

    private fun saveNewExperience() {
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
        startActivity(Intent(this, SignUpEducation::class.java))
    }
}