package com.studynow.groupus

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_dashboard.*

class Dashboard : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.lefttoright , R.anim.right_out)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        auth = Firebase.auth
        val db = Firebase.firestore
        btn_place_order.setOnClickListener {
            startActivity(Intent(this, order :: class.java))
            finish()
        }
        btn_view_order.setOnClickListener {
            startActivity(Intent(this, view :: class.java))
            finish()
        }
        btn_settings.setOnClickListener {
            startActivity(Intent(this, settings :: class.java))
            finish()
        }
        btn_help.setOnClickListener {
            startActivity(Intent(this, help :: class.java))
            finish()
        }
        /*btn_sign_out.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this, MainActivity:: class.java))
            finish()
        }*/


    }
}