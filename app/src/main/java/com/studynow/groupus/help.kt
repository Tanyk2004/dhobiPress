package com.studynow.groupus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_help.*
import kotlinx.android.synthetic.main.activity_view.*

class help : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    var registered_phone = ""
    var help_name = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.righttoleft , R.anim.left_out)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        btn_submit_help.isEnabled = true

        val auth = Firebase.auth
        val db = Firebase.firestore
        btn_back_help.setOnClickListener {
            startActivity(Intent(this, Dashboard :: class.java))
            finish()
        }
        var help_text = findViewById<TextView>(R.id.txt_send_help)
        help_text.text = ""
        db.collection("users").document(auth.currentUser?.uid.toString()).get()
            .addOnSuccessListener {document ->
                registered_phone = document.get("phone_number").toString()
                help_name = document.get("name").toString()
            }
        btn_submit_help.setOnClickListener {
            var help_query = hashMapOf(
                "name" to help_name,
                "registered_phone" to registered_phone,
                "help_msg" to help_text.text.toString()
            )
            db.collection("help_docs").add(help_query)
                .addOnSuccessListener {
                    btn_submit_help.text = "Message Submitted Succesfully!"
                    btn_submit_help.isEnabled = false

                }
        }
    }
}