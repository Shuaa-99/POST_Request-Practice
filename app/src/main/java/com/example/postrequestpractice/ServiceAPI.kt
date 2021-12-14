package com.example.postrequestpractice
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceAPI {
    @GET("test/")
    fun getAPIUsers(): Call<Users>

    @POST("test/")
    fun addUser(@Body userData: UsersItem): Call<UsersItem>
}