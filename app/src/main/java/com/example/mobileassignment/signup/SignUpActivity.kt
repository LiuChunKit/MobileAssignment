package com.example.mobileassignment.signup

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileassignment.LoginActivity
import com.example.mobileassignment.MainActivity
import com.example.mobileassignment.R
import com.example.mobileassignment.models.Education
import com.example.mobileassignment.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.sign_up_activity.*
import kotlinx.android.synthetic.main.sign_up_education.*


class SignUpActivity : AppCompatActivity() {

    private var mAuth = FirebaseAuth.getInstance()
    private var userDatabase = FirebaseDatabase.getInstance().getReference("User")
    private var educationDatabase = FirebaseDatabase.getInstance().getReference("Education")
//    private var workingExpDatabase = FirebaseDatabase.getInstance().getReference("Experience")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)

        mAuth = FirebaseAuth.getInstance()

        val registerBtn = findViewById<Button>(R.id.registerBtn_signUp)
        val registerEducationBtn = findViewById<Button>(R.id.eduConfirmButton)
        val registerExperienceBtn = findViewById<Button>(R.id.expSubmitButton)

        val backBtn = findViewById<Button>(R.id.backBtn_signUp)

        registerBtn.setOnClickListener {
            saveNewUser()
        }

//        registerEducationBtn.setOnClickListener {
//            saveUserEducation()
//        }
//
//        registerExperienceBtn.setOnClickListener {
//            saveUserExperience()
//        }

        backBtn.setOnClickListener {
            backFunction()
        }
    }

    private fun saveUserExperience() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun saveUserEducation() {

//        mAuth = FirebaseAuth.getInstance()
//
//        val schoolName = schoolNameText_edu.text.toString()
//        val fieldOfStudy = fieldOfStudyText_edu.text.toString()
//        val qualification = qualificationText_edu.text.toString()
//        val graduateDate = graduateDateText_edu.text.toString()
//
//        val educationObject = Education(
//            educationId,
//            schoolName,
//            fieldOfStudy,
//            qualification,
//            graduateDate
//        )
//
//        educationDatabase.child(educationId!!).setValue(educationObject).addOnCompleteListener {
//
//            saveUserExperience()
//        }


    }

    private fun saveNewUser() {

        mAuth = FirebaseAuth.getInstance()

        if (usernameText_signUp.text.toString().trim().isEmpty()) {
            usernameText_signUp.error = "Please enter your username"
            usernameText_signUp.requestFocus()
            return
        }

        if (passwordText_signUp.text.toString().trim().isEmpty()) {
            emailText_signUp.error = "Please enter your password"
            emailText_signUp.requestFocus()
            return
        }

        if (confirmPasswordText_signUp.text.toString().trim().isEmpty()) {
            confirmPasswordText_signUp.error = "Please enter your confirm password"
            confirmPasswordText_signUp.requestFocus()
            return
        }

        if (passwordText_signUp.text.toString().trim() != confirmPasswordText_signUp.text.toString().trim()) {
            passwordText_signUp.error = "Your password or confirm password is unmatch"
            passwordText_signUp.requestFocus()
            return

        } else if (confirmPasswordText_signUp.text.toString().trim() != passwordText_signUp.text.toString().trim()) {
            confirmPasswordText_signUp.error = "Your password or confirm password is unmatch"
            confirmPasswordText_signUp.requestFocus()
            return
        }

        if (emailText_signUp.text.toString().trim().isEmpty()) {
            emailText_signUp.error = "Please enter your email"
            emailText_signUp.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailText_signUp.text.toString().trim()).matches()) {
            emailText_signUp.error = "Please enter valid email"
            emailText_signUp.requestFocus()
            return
        }

        if (contactNoText_signUp.text.toString().trim().isEmpty()) {
            contactNoText_signUp.error = "Please enter your contact no"
            contactNoText_signUp.requestFocus()
            return
        }

        if (ageText_signUp.text.toString().trim().isEmpty()) {
            ageText_signUp.error = "Please enter your age"
            ageText_signUp.requestFocus()
            return
        }

        if (addressText_signUp.text.toString().trim().isEmpty()) {
            addressText_signUp.error = "Please enter your address"
            addressText_signUp.requestFocus()
            return
        }

        val userName = usernameText_signUp.text.toString()
        val userRole = "finder"
        val userPassword = passwordText_signUp.text.toString()
        val userConfirmPassword = confirmPasswordText_signUp.text.toString()
        val userEmail = emailText_signUp.text.toString()
        val userContactNo = contactNoText_signUp.text.toString()
        val userAge = ageText_signUp.text.toString().toInt()
        val userAddress = addressText_signUp.text.toString()

        mAuth.createUserWithEmailAndPassword(
            userEmail,
            userPassword
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    val userId = mAuth!!.currentUser!!.uid
                    val userObject = User(
                        userId,
                        userRole,
                        userName,
                        userPassword,
                        userConfirmPassword,
                        userEmail,
                        userContactNo,
                        userAge,
                        userAddress
                    )

                    user?.sendEmailVerification()?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Email sent.

                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Sign Up failed. Please try again",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    updateUI(user)


                    userDatabase.child(userId!!).setValue(userObject).addOnCompleteListener {
                        Toast.makeText(
                            applicationContext,
                            "Your register is successfully.",
                            Toast.LENGTH_SHORT
                        ).show()

//                        saveUserEducation()
                    }

                } else {
                    Toast.makeText(
                        baseContext, "Sign Up failed. Please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)


                }


            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {

    }

    private fun backFunction() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}




//        DatePicker
//        val schoolName = schoolNameText_edu.text.toString().trim()
//        val qualification  = qualification_edu.onItemSelectedListener.toString()
//
//        graduationDate_edu.setOnClickListener() {
//            val c: Calendar = Calendar.getInstance()
//            val currentDay = c.get(Calendar.DAY_OF_MONTH)
//            val currentMonth = c.get(Calendar.MONTH)
//            val currentYear = c.get(Calendar.YEAR)
//
//            val dpd = DatePickerDialog(this,
//                DatePickerDialog.OnDateSetListener
//                { view, year, month, day ->
//                    graduationDate_edu(day.toString() + "/" + (month + 1).toString() + "/" + year.toString())
//
//
//                }, currentYear, currentMonth, currentDay
//            )
//            dpd.show()
//        }


