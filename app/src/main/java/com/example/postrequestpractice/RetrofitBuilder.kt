package com.example.postrequestpractice
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    private var baseURL: Retrofit? = null

    fun getRetrofitBuilder(): Retrofit? {
        baseURL = Retrofit.Builder()
            .baseUrl("https://dojo-recipes.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return baseURL
    }
}