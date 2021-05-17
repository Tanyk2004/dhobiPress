package com.studynow.groupus

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.iterator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_complete_registration.*
var selectedItem:String = " "

class complete_registration : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.righttoleft , R.anim.left_out)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_registration)
        auth = Firebase.auth
        val user : FirebaseUser? = auth.currentUser

        val db = Firebase.firestore


        //********spinner*************
        val spinner = findViewById<Spinner>(R.id.spinner_area)
        val items = arrayOf("sakchi", "dhatkidih", "bishtupur", "kadma", "uliyan", "sonari")

        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            items
        )
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                selectedItem = spinner.selectedItem.toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        //********spinner*************
        var customer_name = findViewById<TextView>(R.id.txt_name_customer)
        var house_number = findViewById<TextView>(R.id.txt_house_number)
        var landmark = findViewById<TextView>(R.id.txt_landmark)
        var phone_number = findViewById<TextView>(R.id.txt_phone_number)
        var area = findViewById<Spinner>(R.id.spinner_area)

        var userinfo = db.collection("users").document(user?.uid.toString()).get()
            .addOnSuccessListener { document ->
                customer_name.text = document.get("name").toString()
                house_number.text = document.get("house_number").toString()
                landmark.text = document.get("landmark").toString()
                phone_number.text = document.get("phone_number").toString()
                area.setSelection(items.indexOf(document.get("area").toString()))
            }


        btn_profile_registration_done.setOnClickListener(){ //converting all the data entries to be sent to the users document
            val user1 = hashMapOf(
                "name" to txt_name_customer.text.toString(),
                "house_number" to txt_house_number.text.toString(),
                "landmark" to txt_landmark.text.toString(),
                "phone_number"  to txt_phone_number.text.toString(),
                "area" to selectedItem.toString()
            )
            db.collection("users").document(user?.uid.toString()).set(user1).addOnSuccessListener {
            Toast.makeText(baseContext, "Account Details Added!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, Dashboard::class.java))
                finish()
            }
        }





            }


        }



