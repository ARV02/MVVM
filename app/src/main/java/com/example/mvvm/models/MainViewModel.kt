package com.example.mvvm.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.api.ApiInterface
import com.example.mvvm.api.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel:ViewModel() {
    lateinit var recyclerListLiveData: MutableLiveData<CardsResponse>

    init {
        recyclerListLiveData = MutableLiveData()
    }

    fun getRecyclerListObserver():MutableLiveData<CardsResponse>{
        return recyclerListLiveData
    }

    fun getAllCards(){
        viewModelScope.launch(Dispatchers.IO) {
            val call = RetrofitInstance.getRetrofit().create(ApiInterface::class.java).showCards()
            recyclerListLiveData.postValue(call.body())




        }

    }
}