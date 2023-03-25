package com.example.testtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {
    lateinit var mail: EditText
    lateinit var pass: EditText
    lateinit var name: EditText
    lateinit var lname: EditText

    val pattern = ("[a-z]{1,100}"+"@"+"[a-z]{1,6}"+"\\."+"[a-z]{1,5}")
    fun isEmailValid(text: String):Boolean {
        return Pattern.compile(pattern).matcher(text).matches()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mail=findViewById(R.id.email)
        pass=findViewById(R.id.password)
        name=findViewById(R.id.name)
        lname=findViewById(R.id.lastName)
    }

    fun toSignin(view: View) {
        val intent = Intent(this@SignupActivity, SigninActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun doRegistragion(view: View) {

        if( mail.text.toString().isNotEmpty()
            && pass.text.toString().isNotEmpty()
            && name.text.toString().isNotEmpty()
            && lname.text.toString().isNotEmpty()
        ) {
            if (isEmailValid(mail.text.toString())) {
                Toast.makeText(this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"ошибка при заполнении поля Email", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this,"Не все поля заполнены", Toast.LENGTH_SHORT).show()
        }
    }
}