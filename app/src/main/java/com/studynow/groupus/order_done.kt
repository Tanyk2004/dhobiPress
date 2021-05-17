package com.studynow.groupus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_order_done.*

class order_done : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.righttoleft , R.anim.left_out)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_done)
        back_to_dashboard.setOnClickListener {
            startActivity(Intent(this, Dashboard :: class.java))
            finish()
        }
        total_disp2.text = "Rs. " + total.toString()

    }
}