package com.example.mvvm.models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.api.ApiInterface
import com.example.mvvm.api.RetrofitInstance
import com.example.mvvm.network.NetworkConnectionInterceptor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    private var recyclerListLiveData: MutableLiveData<CardsResponse> = MutableLiveData()

    fun getRecyclerListObserver():MutableLiveData<CardsResponse>{
        return recyclerListLiveData
    }

    fun getAllCards(){
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.d("Network", "Caught $exception")
        }
        CoroutineScope(Dispatchers.IO).launch(handler){
            val call = RetrofitInstance.getRetrofit()
                .create(ApiInterface::class.java).showCards()
            recyclerListLiveData.postValue(call.body())
        }
    }
}