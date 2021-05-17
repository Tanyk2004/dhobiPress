package com.studynow.groupus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_view.*

class view : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    var arraylist_open_orders = ArrayList<String>()
    var arraylist_closed_orders = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.righttoleft , R.anim.left_out)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        val db = Firebase.firestore
        val auth = Firebase.auth
        val currentUser = auth.currentUser

        btn_back_view.setOnClickListener {
            startActivity(Intent(this, Dashboard :: class.java))
            finish()

        }

        var list_orders = findViewById<ListView>(R.id.txt_open_old_orders)
        var closed_orders = findViewById<ListView>(R.id.txt_old_orders_closed)

        btn_orders_refresh.setOnClickListener {
            db.collection("orders").whereEqualTo("uid" , currentUser?.uid.toString()).whereEqualTo("status" , "open").get()
                .addOnSuccessListener {document ->
                    for (i in document){
                        arraylist_open_orders.add("Name:" + i.get("name").toString() + "\nAddress:" + i.get("house_number").toString() + "," + i.get("landmark").toString() + "," + i.get("area").toString() + "\nTotal: " + i.get("total").toString() + "\nAt: " + i.get("order_time").toString())
                    }
                }
            db.collection("orders").whereEqualTo("uid" , currentUser?.uid.toString()).whereEqualTo("status" , "closed").get()
                .addOnSuccessListener {document ->
                    for (i in document){
                        arraylist_closed_orders.add("Name:" + i.get("name").toString() + "\nAddress:" + i.get("house_number").toString() + "," + i.get("landmark").toString() + "," + i.get("area").toString() + "\nTotal: " + i.get("total").toString() + "\nAt: " + i.get("order_time").toString())
                    }
                }
            var open_adapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arraylist_open_orders
            )
            list_orders.adapter = open_adapter
            var closed_adapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arraylist_closed_orders)
            closed_orders.adapter = closed_adapter
            arraylist_closed_orders = ArrayList()
            arraylist_closed_orders = ArrayList()
        }
    }

//.whereEqualTo("uid" , currentUser?.uid.toString()).whereEqualTo("status" , "closed")
}