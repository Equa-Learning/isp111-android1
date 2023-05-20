package com.example.testtest.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.testtest.R
import java.util.regex.Pattern

class SigninActivity : AppCompatActivity() {

    var pref: SharedPreferences? = null

    lateinit var mail: EditText
    lateinit var pass: EditText
    lateinit var remember: CheckBox

    val pattern = ("[a-z]{1,100}" + "@" + "[a-z]{1,6}" + "\\." + "[a-z]{1,5}")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        mail = findViewById(R.id.signinEmailField)
        pass = findViewById(R.id.signinPasswordField)
        remember = findViewById(R.id.rememberMe)
        pref = getSharedPreferences("TABLEE", MODE_PRIVATE)
        remember.isChecked = pref?.getBoolean("signinDoRemember", false) ?: false
        if (remember.isChecked) {
            tryRestore()
        }
    }

    private fun tryRestore() {
        mail.setText(pref?.getString("signinEmail", ""))
        pass.setText(pref?.getString("signinPass", ""))
    }

    private fun saveState(check: Boolean) {
        val editor = pref?.edit()
        editor?.putBoolean("signinDoRemember", check)
        editor?.apply()
    }

    private fun saveData(mail: String, pass: String) {
        val editor = pref?.edit()
        editor?.putString("signinEmail", mail)
        editor?.putString("signinPass", pass)
        editor?.apply()
    }

    private fun deleteAllSavedPrefs() {
        val editor = pref?.edit()
        editor?.clear()
        editor?.apply()
    }

    fun isEmailValid(text: String): Boolean {
        return Pattern.compile(pattern).matcher(text).matches()
    }

    fun doLogin(view: View) {
        if (mail.text.toString().isNotEmpty() && pass.text.toString().isNotEmpty()) {
            if (isEmailValid(mail.text.toString())) {
                if (remember.isChecked) {
                    saveState(remember.isChecked)
                    saveData(mail.text.toString(), pass.text.toString())
                } else {
                    deleteAllSavedPrefs()
                }
                // проверяем что логин и пароль верные
                if (checkAuth()) {
                    toKlaus()
                } else {
                    val alert = AlertDialog.Builder(this)
                        .setTitle("Логин и пароль не соответствуют зарегистрированным")
                        .setPositiveButton("OK", null)
                        .create()
                        .show()
                }
            } else {
                Toast.makeText(this, "ошибка при заполнении поля Email", Toast.LENGTH_SHORT).show()
            }
        } else {
            val alert = AlertDialog.Builder(this)
                .setTitle("Заполните текстовые поля")
                .setPositiveButton("OK", null)
                .create()
                .show()
        }
    }

    fun checkAuth(): Boolean {
        if (
            pref?.getString("userEmail","") ?: "" == mail.text.toString()
            && pref?.getString("userPass","") ?: "" == pass.text.toString()
        ) {
            return true;
        }
        return false;
    }

    fun toKlaus() {
        val intent = Intent(this@SigninActivity, ChooseVersionActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun toRegistragion(view: View) {
        val intent = Intent(this@SigninActivity, SignupActivity::class.java)
        startActivity(intent)
        finish()
    }
}