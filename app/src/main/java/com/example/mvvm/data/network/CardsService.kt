package com.example.mvvm.data.network

import com.example.mvvm.core.RetrofitInstance
import com.example.mvvm.data.models.CardsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CardsService {
    private val retrofit = RetrofitInstance.getRetrofit()

    suspend fun getAllCards(): CardsResponse? {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(ApiInterface::class.java).showCards()
            response.body()
        }
    }
}