package com.example.testtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.testtest.adapters.Frame_adapter
import com.example.testtest.data.KlausFrames

class KlausActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_klaus)
        val framesView:RecyclerView= findViewById(R.id.klaus_frames)
        framesView.adapter = Frame_adapter(this, KlausFrames().list)
    }
}