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
import com.example.mobileassignment.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.sign_up_activity.*


class SignUpActivity : AppCompatActivity() {

    private var mAuth = FirebaseAuth.getInstance()
    private var userDatabase = FirebaseDatabase.getInstance().getReference("User")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)

        mAuth = FirebaseAuth.getInstance()

        val registerBtn = findViewById<Button>(R.id.registerBtn_signUp)
        val backBtn = findViewById<Button>(R.id.backBtn_signUp)

        registerBtn.setOnClickListener {
            saveNewUser()
        }

        backBtn.setOnClickListener {
            backFunction()
        }
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

//        val userId = mAuth.uid
//        val userName = findViewById<EditText>(R.id.usernameText_signUp)
//        val userPassword = findViewById<EditText>(R.id.passwordText_signUp)
//        val userConfirmPassword = findViewById<EditText>(R.id.confirmPasswordText_signUp)
//        val userEmail = findViewById<EditText>(R.id.emailText_signUp)
//        val userContactNo = findViewById<EditText>(R.id.contactNoText_signUp)
//        val userAge = findViewById<EditText>(R.id.ageText_signUp)
//        val userAddress = findViewById<EditText>(R.id.addressText_signUp)


        val userName = usernameText_signUp.text.toString()
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
                        userName,
                        userPassword,
                        userConfirmPassword,
                        userEmail,
                        userContactNo,
                        userAge,
                        userAddress
                    )

                    startActivity(Intent(this, LoginActivity::class.java))
                    updateUI(user)


                    userDatabase.child(userId!!).setValue(userObject).addOnCompleteListener {
                        Toast.makeText(
                            applicationContext,
                            "Your register is successfully.",
                            Toast.LENGTH_SHORT
                        ).show()
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
