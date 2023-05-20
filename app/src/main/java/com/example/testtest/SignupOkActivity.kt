package com.example.testtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View

class SignupOkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_ok)

        var timer= object : CountDownTimer(2000,1000) {
            override fun onTick(millisUntilFinished: Long) {
                //
            }

            override fun onFinish() {
                goToSignIn()
            }
        }
    }

    fun goToSignIn(){
        var intent = Intent(this@SignupOkActivity, SigninActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goBack(view: View) {
        goToSignIn();

    }
}