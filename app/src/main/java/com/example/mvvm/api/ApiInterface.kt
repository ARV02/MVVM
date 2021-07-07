package com.example.mvvm.api

import com.example.mvvm.models.CardsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("viewAllCards")
    suspend fun showCards():Response<CardsResponse>
}