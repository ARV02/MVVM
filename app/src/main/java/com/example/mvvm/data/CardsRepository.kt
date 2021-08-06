package com.example.mvvm.data

import com.example.mvvm.data.models.CardsResponse
import com.example.mvvm.data.network.CardsService

class CardsRepository {
    private val api = CardsService()

    suspend fun getAllCards(): CardsResponse? {
        return api.getAllCards()
    }
}