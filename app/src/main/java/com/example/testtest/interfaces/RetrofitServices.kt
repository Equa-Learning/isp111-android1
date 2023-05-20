package com.example.testtest.interfaces

import com.example.testtest.data.model.data
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("feelings")
    fun getImages():Call<data>
}