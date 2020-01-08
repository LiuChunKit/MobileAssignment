package com.example.mobileassignment

import android.content.Intent
import android.os.Bundle
import android.renderscript.Script
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileassignment.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.resetpassword_activity.*

class ResetPassword: AppCompatActivity() {

    private var mAuth = FirebaseAuth.getInstance()
    private var userDatabase = FirebaseDatabase.getInstance().getReference("User")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resetpassword_activity)

        mAuth = FirebaseAuth.getInstance()

        val resetButton = findViewById<Button>(R.id.confirmButton_resetPassword)
        val backButton = findViewById<Button>(R.id.cancelButton_resetPassword)

        resetButton.setOnClickListener() {
            resetPassword()
        }

        backButton.setOnClickListener() {
            backFuntion()
        }
    }


    private fun resetPassword() {
        mAuth = FirebaseAuth.getInstance()

        val usernameInput = usernameText_resetPassword.text.toString()
        val passwordInput = passwordText_resetPassword.text.toString()

        object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val username = ds.child("user_email").getValue(String::class.java)
                    val password = ds.child("user_password").getValue(String::class.java)

                    if (usernameInput == username && passwordInput == password) {
                        val userPassword = newPasswordText_resetPassword.text.toString()

                        val userObject = User(userPassword)

                        userDatabase.child(username).setValue(userObject).addOnCompleteListener {
                            Toast.makeText(
                                applicationContext,
                                "Your register is successfully.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
    }

    private fun backFuntion() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}