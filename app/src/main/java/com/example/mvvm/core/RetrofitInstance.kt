package com.example.mvvm.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {

        fun getRetrofit():Retrofit{
            return Retrofit.Builder()
                .baseUrl("http://192.168.1.81:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}