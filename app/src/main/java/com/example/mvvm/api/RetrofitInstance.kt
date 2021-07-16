package com.example.mvvm.api

import com.example.mvvm.network.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {

        fun getRetrofit():Retrofit{
            //val okkHttpClient =OkHttpClient.Builder()
                //.addInterceptor(networkConnectionInterceptor)
                //.build()
            return Retrofit.Builder()
                //.client(okkHttpClient)
                .baseUrl("http://192.168.1.81:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}