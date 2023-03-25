package com.example.testtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class SigninActivity : AppCompatActivity() {
    lateinit var mail:EditText
    lateinit var pass:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        mail=findViewById(R.id.email)
        pass=findViewById(R.id.password)

    }

    fun doLogin(view: View) {
        if(mail.text.toString().isNotEmpty() && pass.text.toString().isNotEmpty()) {
            val intent = Intent(this@SigninActivity, SignupActivity::class.java)
            startActivity(intent)
            finish()
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