package com.studynow.groupus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_help.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class sign_up : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.righttoleft , R.anim.left_out)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        btn_back_help2.setOnClickListener {
            startActivity(Intent(this, MainActivity :: class.java))
            finish()
        }
        auth = Firebase.auth
       btn_signup.setOnClickListener{
            var allowed_login = checkCreds()
            if(allowed_login){
            auth.createUserWithEmailAndPassword(txt_signup_email.text.toString() , txt_password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        startActivity(Intent(this, complete_registration::class.java))
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(baseContext, "Authentication failed, try again",
                            Toast.LENGTH_SHORT).show()
                    }

                    // ...
                }}
        }
    }
    private fun checkCreds(): Boolean {
        if(txt_signup_email.text.toString().isEmpty()){
            txt_signup_email.error = "Please enter an email"
            txt_signup_email.requestFocus()
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(txt_signup_email.text.toString()).matches()){
            txt_signup_email.error = "Please enter valid email"
            txt_signup_email.requestFocus()
            return false
        }
        if(txt_password.text.toString().isEmpty()){
            txt_password.error = "Please enter a password"
            txt_password.requestFocus()
            return false
        }
        if(txt_signup_email.text.toString().isEmpty()){
            txt_password.error = "Please enter a password"
            txt_password.requestFocus()
            return false
        }
        else{
            return true
        }
    }
}