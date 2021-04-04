package com.example.barbell

import Extensions.AuthenticationActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AuthenticationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_register.setOnClickListener {
            register()
        }

        // Go to LoginActivity
        tv_have_account.setOnClickListener {
            var intent = Intent(applicationContext, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent)
        }
    }

    private fun register() {
        if (notEmpty()) {
            userEmail = et_email.text.toString().trim()
            userPassword = et_password.text.toString().trim()

            // Create User
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                    if(task.isComplete) {
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        Toast.makeText(
                            this@RegisterActivity,
                            "You registered successfully.",
                            Toast.LENGTH_SHORT
                        ).show()

                        var intent = Intent(applicationContext, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra(
                            "user_id",
                            FirebaseAuth.getInstance().currentUser!!.uid
                            )
                        intent.putExtra(
                            "user_email",
                            userEmail
                            )
                        startActivity(intent)
                        finish()

                    } else {
                        Toast.makeText(
                            this@RegisterActivity, task.exception!!.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }

}