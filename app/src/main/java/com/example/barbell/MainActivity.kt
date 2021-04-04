package com.example.barbell

import Extensions.AuthenticationActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AuthenticationActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_name.text = intent.getStringExtra("user_id")
        tv_sport.text = intent.getStringExtra("user_email")

        textView3.setOnClickListener {
            logOut()
        }
    }
}