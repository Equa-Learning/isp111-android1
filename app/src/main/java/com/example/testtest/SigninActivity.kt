package com.example.testtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.regex.Pattern

class SigninActivity : AppCompatActivity() {
    lateinit var mail:EditText
    lateinit var pass:EditText

    val pattern = ("[a-z]{1,100}"+"@"+"[a-z]{1,6}"+"\\."+"[a-z]{1,5}")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        mail=findViewById(R.id.signinEmailField)
        pass=findViewById(R.id.signinPasswordField)

    }

    fun isEmailValid(text: String):Boolean {
        return Pattern.compile(pattern).matcher(text).matches()
    }

    fun doLogin(view: View) {
        if(mail.text.toString().isNotEmpty() && pass.text.toString().isNotEmpty()) {
            if (isEmailValid(mail.text.toString())) {
                val intent = Intent(this@SigninActivity, BackToStartActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this,"ошибка при заполнении поля Email", Toast.LENGTH_SHORT).show()
            }
        } else {
            val alert=AlertDialog.Builder(this)
                .setTitle("Заполните текстовые поля")
                .setPositiveButton("OK",null)
                .create()
                .show()
        }
    }
    fun toRegistragion(view: View) {
        val intent = Intent(this@SigninActivity, SignupActivity::class.java)
        startActivity(intent)
        finish()
    }
}