package com.example.mobileassignment

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileassignment.models.Jobs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.add_job.*


class addJobActivity : AppCompatActivity() {



    private var mAuth = FirebaseAuth.getInstance()

    private lateinit var jobDatabase: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.add_job)



        mAuth = FirebaseAuth.getInstance()



        val addBtn = findViewById<Button>(R.id.addjobbtn)

        val backBtn = findViewById<Button>(R.id.backbtn)



        addBtn.setOnClickListener {

            saveNewJobFunction()

        }



        backBtn.setOnClickListener {

            backFunction()

        }

    }



    private fun saveNewJobFunction() {

        mAuth = FirebaseAuth.getInstance()







        val jobPosition = addJobsPosition.text.toString().trim()

        val jobDescription = addJobsDescription.text.toString().trim()

        val jobSalary = addJobsSalary.text.toString().trim()

        val jobRequirement = addJobsRequirement.text.toString().trim()



        if (TextUtils.isEmpty(jobPosition)) {

            Toast.makeText(

                this,

                "Please enter the position",

                Toast.LENGTH_SHORT

            ).show()

        }



        if (TextUtils.isEmpty(jobDescription)) {

            Toast.makeText(

                this,

                "Please enter the description",

                Toast.LENGTH_SHORT

            ).show()

        }



        if (TextUtils.isEmpty(jobSalary)) {

            Toast.makeText(

                this,

                "Please enter the salary",

                Toast.LENGTH_SHORT

            ).show()

        }



        if (TextUtils.isEmpty(jobRequirement)) {

            Toast.makeText(

                this,

                "Please enter the requirement",

                Toast.LENGTH_SHORT

            ).show()

        }



        jobDatabase = FirebaseDatabase.getInstance().getReference()

            AuthStateListener { firebaseAuth ->
                val firebaseUser = firebaseAuth.currentUser
                if (firebaseUser != null) {
                    val userId = firebaseUser.uid
                    val userEmail = firebaseUser.email
                    val userAddress = firebaseUser
                }
            }

        val newJobid = jobDatabase.push().key



        if(newJobid!=null) {

            val jobb = Jobs(jobPosition, jobDescription, jobSalary, jobRequirement, newJobid )

            jobDatabase.child("Job").child(newJobid).setValue(jobb).addOnCompleteListener {

                Toast.makeText(

                    applicationContext,

                    "Job Saved Successfully",

                    Toast.LENGTH_SHORT

                ).show()

            }
        }





        /*val newJobid = jobDatabase.key

        if(newJobid!= null) {



            val jobb = Jobs(newJobid, jobPosition, jobDescription, jobSalary, jobRequirement)



            jobDatabase.child(newJobid).setValue(jobb).addOnCompleteListener {

                Toast.makeText(

                    applicationContext,

                    "Job Saved Successfully",

                    Toast.LENGTH_SHORT

                ).show()

            }

    } else{

        Toast.makeText(

            applicationContext,

            "Try again",

            Toast.LENGTH_SHORT

        ).show()

    }*/



        /*val jobb = Jobs(jobPosition, jobDescription, jobSalary, jobRequirement)



        jobDatabase.child().setValue(jobb)



            Toast.makeText(

                applicationContext,

                "Job Saved Successfully",

                Toast.LENGTH_SHORT

            ).show()*/



    }



    private fun backFunction(){



    }

}