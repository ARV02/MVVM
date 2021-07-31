package com.example.mvvm.di

import com.example.mvvm.api.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providerRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.81:3000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providerApiInterface(retrofit:Retrofit):ApiInterface{
        return retrofit.create(ApiInterface::class.java)
    }
}