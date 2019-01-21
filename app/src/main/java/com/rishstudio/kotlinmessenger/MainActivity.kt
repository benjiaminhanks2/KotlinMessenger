package com.rishstudio.kotlinmessenger

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        already_have_account_textview.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        register_button_register.setOnClickListener {
            performRegister()
        }
    }

    private fun performRegister() {
        val email = email_edittext_register.text.toString()
        val password = password_edittext_register.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please Enter Username and password", Toast.LENGTH_LONG).show()
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!it.isSuccessful) return@addOnCompleteListener

            Log.d("Main", "Successfully Created User with uid ${it.result?.user?.uid}")

        }.addOnFailureListener {
            Log.d("Main", "Failed to create user ${it.message}")
            Toast.makeText(this, "Failed to create user ${it.message}", Toast.LENGTH_LONG).show()
        }
    }
}
