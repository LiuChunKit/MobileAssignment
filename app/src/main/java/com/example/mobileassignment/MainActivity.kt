package com.example.mobileassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mobileassignment.signup.SignUpActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginBtn = findViewById<Button>(R.id.loginBtn_mainPage)
        val registerBtn = findViewById<Button>(R.id.registerBtn_mainPage)

        loginBtn.setOnClickListener(){

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        registerBtn.setOnClickListener(){

            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }
}
