package com.studynow.groupus

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.postDelayed
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_view.*
import java.lang.Runnable as Runnable1

class view : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    var arraylist_open_orders = ArrayList<String>()
    var arraylist_closed_orders = ArrayList<String>()
    private val channel_id = "channel_id_example_01"
    private val notificationId = 101
    private var count = 0
    var num_orders = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.righttoleft , R.anim.left_out)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        createNotificationChannel()


        val db = Firebase.firestore
        val auth = Firebase.auth
        val currentUser = auth.currentUser
        var list_orders = findViewById<ListView>(R.id.txt_open_old_orders)
        var closed_orders = findViewById<ListView>(R.id.txt_old_orders_closed)



        refresh(1000)
        btn_back_view.setOnClickListener {
            startActivity(Intent(this, Dashboard :: class.java))
            finish()

        }







        btn_orders_refresh.setOnClickListener {
            sendNotification()
            db.collection("orders").whereEqualTo("status" , "open").get()
                .addOnSuccessListener {document ->
                    for (i in document){
                        arraylist_open_orders.add("Name:" + i.get("name").toString() + "\nAddress:" + i.get("house_number").toString() + "," + i.get("landmark").toString() + "," + i.get("area").toString() + "\nTotal: " + i.get("total").toString() + "\nAt: " + i.get("order_time").toString())
                    }
                }
            num_orders = arraylist_open_orders.size
            Toast.makeText(baseContext, num_orders.toString() , Toast.LENGTH_SHORT).show()
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
            arraylist_open_orders = ArrayList()
            arraylist_closed_orders = ArrayList()
        }
    }


    private fun createNotificationChannel(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val name  = "Order notifications"
        val descriptionText = "Get notified when you get a new order"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channel_id , name , importance).apply{
            description = descriptionText
        }
        val notificationManager  : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
    }
private fun sendNotification(){
    val builder  = NotificationCompat.Builder(this, channel_id)
        .setSmallIcon(R.drawable.ic_logo_vector)
        .setContentTitle("New Order!")
        .setContentText("you have a new order")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
    with(NotificationManagerCompat.from(this)){
        notify(notificationId , builder.build())
    }
}
public fun content(){
    val db = Firebase.firestore
    val auth = Firebase.auth
    val currentUser = auth.currentUser
    var list_orders = findViewById<ListView>(R.id.txt_open_old_orders)
    var closed_orders = findViewById<ListView>(R.id.txt_old_orders_closed)

    db.collection("orders").whereEqualTo("status" , "open").get()
        .addOnSuccessListener {document ->
            for (i in document){
                arraylist_open_orders.add("Name:" + i.get("name").toString() + "\nAddress:" + i.get("house_number").toString() + "," + i.get("landmark").toString() + "," + i.get("area").toString() + "\nTotal: " + i.get("total").toString() + "\nAt: " + i.get("order_time").toString())
            }
        }
    var local_num = arraylist_open_orders.size

    if(local_num > num_orders){
        sendNotification()
        num_orders = local_num
    }
    db.collection("orders").whereEqualTo("status" , "closed").get()
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
    arraylist_open_orders = ArrayList()
    arraylist_closed_orders = ArrayList()

}
public fun refresh(milliseconds : Int){
    var mainHandler = Handler(Looper.getMainLooper())
    mainHandler.post(object : java.lang.Runnable{
        override fun run(){
                content()
            mainHandler.postDelayed(this, 1000)

        }
    })
}
//.whereEqualTo("uid" , currentUser?.uid.toString()).whereEqualTo("status" , "closed")
}


