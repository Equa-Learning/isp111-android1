package com.example.testtest

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {
    lateinit var mail: EditText
    lateinit var pass: EditText
    lateinit var pass2: EditText
    lateinit var name: EditText
    lateinit var lname: EditText

    var pref: SharedPreferences? = null
    var users: SharedPreferences? = null

    val pattern = ("[a-z]{1,100}"+"@"+"[a-z]{1,6}"+"\\."+"[a-z]{1,5}")
    fun isEmailValid(text: String):Boolean {
        return Pattern.compile(pattern).matcher(text).matches()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mail=findViewById(R.id.email)
        pass=findViewById(R.id.password)
        pass2=findViewById(R.id.password2)
        name=findViewById(R.id.name)
        lname=findViewById(R.id.lastName)
        pref = getSharedPreferences("TABLEE", MODE_PRIVATE)
        if (pref?.getBoolean("signinDoRemember", false) ?: false) {

        }
    }

    private fun tryRestore() {
        mail.setText(pref?.getString("signinEmail", ""))
        pass.setText(pref?.getString("signinPass", ""))
    }



    private fun saveUser(mail: String, pass: String, name: String, lastname: String) {
        val editor = pref?.edit()
        editor?.putString("userEmail", mail)
        editor?.putString("userPass", pass)
        editor?.putString("userName", name)
        editor?.putString("userLastName", lastname)
        editor?.apply()
    }

    fun toSignin(view: View) {
        val intent = Intent(this@SignupActivity, SigninActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun doRegistragion(view: View) {

        if( mail.text.toString().isNotEmpty()
            && pass.text.toString().isNotEmpty()
            && pass2.text.toString().isNotEmpty()
            && name.text.toString().isNotEmpty()
            && lname.text.toString().isNotEmpty()
        ) {

            if (isEmailValid(mail.text.toString())) {
                if (pass.text.toString() == pass2.text.toString()) {
                    saveUser(
                        mail.text.toString(), pass.text.toString(), name.text.toString(), lname.text.toString()
                    )

                    val intent = Intent(this@SignupActivity, SignupOkActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Пароль и повтор не совпадают", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this,"ошибка при заполнении поля Email", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this,"Не все поля заполнены", Toast.LENGTH_SHORT).show()
        }
    }
}