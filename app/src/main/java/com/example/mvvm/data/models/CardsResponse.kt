package com.example.mvvm.data.models

import com.google.gson.annotations.SerializedName

data class CardsResponse (
    @SerializedName("cardDetails")
    var cardDetails:MutableList<CardDetails>
    )

data class CardDetails(
    @SerializedName("cardId")
    var cardId:String,

    @SerializedName("cardType")
    var cardType: String,

    @SerializedName("cardExpDetails")
    var cardExpDetails: String,

    @SerializedName("cardLast4")
    var cardLast4: String
)