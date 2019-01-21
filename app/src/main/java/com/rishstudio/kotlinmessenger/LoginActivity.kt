package com.rishstudio.kotlinmessenger

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dont_have_account_textview.setOnClickListener {
            val signupIntent = Intent(this, RegisterActivity::class.java)
            startActivity(signupIntent)
        }

        login_button_login.setOnClickListener {
           performLogin()
        }
    }

    private fun performLogin() {
        val email = email_edittext_login.text.toString()
        val password = password_edittext_login.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please Enter Username and password", Toast.LENGTH_LONG).show()
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (!it.isSuccessful) return@addOnCompleteListener

            Log.d("Main", "Successfully Logged User with uid ${it.result?.user?.uid}")
        }.addOnFailureListener {
            Log.d("Main","Failed to log in user ${it.localizedMessage}")
        }
    }
}