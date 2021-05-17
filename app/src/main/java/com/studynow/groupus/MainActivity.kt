package com.studynow.groupus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.lefttoright , R.anim.right_out)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        go_to_signup.setOnClickListener{
            startActivity(Intent(this, sign_up::class.java))
            finish()
        }
        btn_login.setOnClickListener{
        checkCreds()
            auth.signInWithEmailAndPassword(login_email.text.toString(), login_password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                        // ...
                    }

                    // ...
                }
        }

    }
   public override fun onStart(){
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    private fun updateUI(user: FirebaseUser?){
        if(user != null){
            startActivity(Intent(this, Dashboard::class.java))
        Toast.makeText(baseContext,"logged in", Toast.LENGTH_SHORT).show()
            finish()

        }
        else {

        }
    }
    public fun checkCreds(){
        if(login_email.text.toString().isEmpty()){
            txt_signup_email.error = "Please enter an email"
            txt_signup_email.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(login_email.text.toString()).matches()){
            txt_signup_email.error = "Please enter valid email"
            txt_signup_email.requestFocus()
            return
        }
        if(login_password.text.toString().isEmpty()){
            txt_password.error = "Please enter a password"
            txt_password.requestFocus()
            return
        }
    }
}