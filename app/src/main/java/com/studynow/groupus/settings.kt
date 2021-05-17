package com.studynow.groupus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_view.*

class settings : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.righttoleft , R.anim.left_out)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        auth = Firebase.auth
        btn_back_settings.setOnClickListener {
            startActivity(Intent(this, Dashboard :: class.java))
            finish()
        }

        btn_sign_out.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this, MainActivity:: class.java))
            finish()
    }
        btn_edit_profile.setOnClickListener {
            startActivity(Intent(this, complete_registration :: class.java))
            finish()

        }
}}