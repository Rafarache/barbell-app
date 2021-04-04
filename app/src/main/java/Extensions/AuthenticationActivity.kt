package Extensions

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.barbell.LoginActivity
import com.example.barbell.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

public open class AuthenticationActivity : AppCompatActivity() {

    lateinit var userEmail: String
    lateinit var userPassword: String

    fun notEmpty(): Boolean =
        et_email.text.toString().trim().isNotEmpty() && et_password.text.toString().trim().isNotEmpty()

    fun logOut() {
        FirebaseAuth.getInstance().signOut()
        var intent = Intent(applicationContext, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent)
        finish()
    }

    fun startMainActivity () {
        var intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(
            "user_id",
            FirebaseAuth.getInstance().currentUser!!.uid
        )
        intent.putExtra(
            "user_email",
            FirebaseAuth.getInstance().currentUser!!.email
        )
        startActivity(intent)
        finish()
    }
}