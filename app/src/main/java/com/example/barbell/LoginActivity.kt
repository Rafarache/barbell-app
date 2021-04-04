package com.example.barbell

import Extensions.AuthenticationActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.et_email
import kotlinx.android.synthetic.main.activity_login.et_password
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AuthenticationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (FirebaseAuth.getInstance().getCurrentUser()!=null) {
            startMainActivity()
        }

        btn_login.setOnClickListener {
            login()
        }

        // Go to Register Activity
        tv_createaccount.setOnClickListener {
            var intent = Intent(applicationContext, RegisterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent)
        }
    }

    private fun login() {
        if (notEmpty()) {
            userEmail = et_email.text.toString().trim()
            userPassword = et_password.text.toString().trim()

            // Create User
            FirebaseAuth.getInstance().signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if(task.isComplete) {
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        Toast.makeText(
                            this@LoginActivity,
                            "You logged in successfully.",
                            Toast.LENGTH_SHORT
                        ).show()

                        startMainActivity()

                    } else {
                        Toast.makeText(
                            this@LoginActivity, task.exception!!.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}