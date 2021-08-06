package com.example.mvvm.data.network

import com.example.mvvm.data.models.CardsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("viewAllCards")
    suspend fun showCards():Response<CardsResponse>
}