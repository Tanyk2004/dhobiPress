package com.studynow.groupus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.activity_view.*
import java.text.SimpleDateFormat
import java.util.*

var total : Int = 0
var order_map = hashMapOf<String, Int>()
class order : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.righttoleft , R.anim.left_out)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        auth = Firebase.auth
        val db = Firebase.firestore
        //***************** user info
        var area = ""
        var house_number = ""
        var landmark = ""
        var name = ""
        var phn_num = ""
        val currentUser = auth.currentUser
        db.collection("users").document(currentUser?.uid.toString()).get()
            .addOnSuccessListener { document ->
                area = document.get("area").toString()
                house_number = document.get("house_number").toString()
                landmark = document.get("landmark").toString()
                name = document.get("name").toString()
                phn_num = document.get("phone_number").toString()
            }
        //***************** user info
        var simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:MM:SS")

        btn_back_order.setOnClickListener {
            startActivity(Intent(this, Dashboard :: class.java))
            finish()
        }
        btn_order.setOnClickListener {

            if(qty_jeans_i.text.toString().trim().toInt() != 0){
             order_map["jeans - iron"] = qty_jeans_i.text.toString().trim().toInt()
         }
            if(qty_jeans_w.text.toString().trim().toInt() != 0){
                order_map["jeans - washing"] = qty_jeans_w.text.toString().trim().toInt()
            }
            if(qty_jeans_iw.text.toString().trim().toInt() != 0){
                order_map["jeans - iron & washing"] = qty_jeans_iw.text.toString().trim().toInt()
            }

            if(qty_shirt_i.text.toString().trim().toInt() != 0){order_map["shirts - iron"] = qty_shirt_i.text.toString().trim().toInt()}
            if(qty_shirt_w.text.toString().trim().toInt() != 0){order_map["shirts - washing"] = qty_shirt_w.text.toString().trim().toInt()}
            if(qty_shirt_iw.text.toString().trim().toInt() != 0){order_map["shirts - iron & washing"] = qty_shirt_iw.text.toString().trim().toInt()}

            if(qty_trousers_i.text.toString().trim().toInt() != 0){order_map["trousers - iron"] = qty_trousers_i.text.toString().trim().toInt()}
            if(qty_trousers_w.text.toString().trim().toInt() != 0){order_map["trousers - iron"] = qty_trousers_w.text.toString().trim().toInt()}
            if(qty_trousers_iw.text.toString().trim().toInt() != 0){order_map["trousers - iron"] = qty_trousers_iw.text.toString().trim().toInt()}

            if(qty_suit.text.toString().trim().toInt() != 0){order_map["Suit - DryCleaning"] = qty_suit.text.toString().trim().toInt()}

            if(qty_saree_i.text.toString().trim().toInt() != 0){order_map["Saree -iron"] = qty_saree_i.text.toString().trim().toInt()}
            if(qty_saree_w.text.toString().trim().toInt() != 0){order_map["Saree - washing"] = qty_saree_w.text.toString().trim().toInt()}
            if(qty_saree_iw.text.toString().trim().toInt() != 0){order_map["Saree - iron & washing"] = qty_saree_iw.text.toString().trim().toInt()}

            if(qty_sweatshirt.text.toString().trim().toInt() != 0){order_map["sweatshirts"] = qty_sweatshirt.text.toString().trim().toInt()}

            if(qty_kurta_i.text.toString().trim().toInt() != 0){order_map["kurta - iron"] = qty_kurta_i.text.toString().trim().toInt()}
            if(qty_kurta_w.text.toString().trim().toInt() != 0){order_map["kurta - washing"] = qty_kurta_w.text.toString().trim().toInt()}
            if(qty_kurta_iw.text.toString().trim().toInt() != 0){order_map["kurta - iron & washing"] = qty_kurta_iw.text.toString().trim().toInt()}

            if(qty_shorts_i.text.toString().trim().toInt() != 0){order_map["shorts - iron"] = qty_shorts_i.text.toString().trim().toInt()}
            if(qty_shorts_w.text.toString().trim().toInt() != 0){order_map["shorts - washing"] = qty_shorts_w.text.toString().trim().toInt()}
            if(qty_shorts_iw.text.toString().trim().toInt() != 0){order_map["shorts - iron & washing"] = qty_shorts_iw.text.toString().trim().toInt() }

            if(qty_undershirt.text.toString().trim().toInt() != 0){order_map["undershirt"] = qty_undershirt.text.toString().trim().toInt()}

            if(qty_underwear.text.toString().trim().toInt() != 0){order_map["underwear"] = qty_underwear.text.toString().trim().toInt()}
        total = (qty_shirt_i.text.toString().trim().toInt() * 10 + qty_shirt_w.text.toString().trim().toInt() * 12.5 + qty_shirt_iw.text.toString().trim().toInt() * 20+ qty_jeans_i.text.toString().trim().toInt() * 10+ qty_jeans_w.text.toString().trim().toInt() * 12.5+ qty_jeans_iw.text.toString().trim().toInt()* 20+ qty_trousers_i.text.toString().trim().toInt() * 10 + qty_trousers_w.text.toString().trim().toInt()* 12.5 + qty_trousers_iw.text.toString().trim().toInt()* 20 + qty_suit.text.toString().trim().toInt()* 215+ qty_saree_i.text.toString().trim().toInt() * 35+ qty_saree_w.text.toString().trim().toInt() * 45+ qty_saree_iw.text.toString().trim().toInt()* 60+ qty_sweatshirt.text.toString().trim().toInt() *40+ qty_kurta_i.text.toString().trim().toInt() * 12.5+ qty_kurta_w.text.toString().trim().toInt()* 17.5 + qty_kurta_iw.text.toString().trim().toInt()* 25+ qty_shorts_i.text.toString().trim().toInt() *  5 + qty_shorts_w.text.toString().trim().toInt() * 10+ qty_shorts_iw.text.toString().trim().toInt() * 12.5+ qty_underwear.text.toString().trim().toInt() *7.5+ qty_undershirt.text.toString().trim().toInt() * 10).toInt()
            var currentTime : String = simpleDateFormat.format(Date())
            var order_data = hashMapOf(
                "name" to name,
                "house_number" to house_number,
                "area" to area,
                "landmark" to landmark,
                "phone_number" to phn_num,
                "uid" to currentUser?.uid.toString(),
                "order_map" to order_map,
                "total" to total,
                "status" to "open",
                "order_time" to currentTime
            )
            db.collection("orders").add(order_data)
                .addOnSuccessListener { Toast.makeText(baseContext, "Order has been placed" , Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, order_done :: class.java))
                    finish()
                }


        }
    }
}