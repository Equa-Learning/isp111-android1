package com.example.testtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class BackToStartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back_to_start)
    }

    fun goBack(view: View) {
        var intent = Intent(this@BackToStartActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}