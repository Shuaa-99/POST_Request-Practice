package com.example.postrequestpractice
import retrofit2.Call
import retrofit2.http.*

interface ServiceAPI {
    @GET("test/")
    fun getAPIUsers(): Call<Users>

    @POST("test/")
    fun addUser(@Body userData: UsersItem): Call<UsersItem>

    @PUT("/test/{id}")
    fun updateUser(@Path("id")id:Int, @Body userData: UsersItem):Call<UsersItem>

    @DELETE("/test/{id}")
    fun deleteUser(@Path("id")id:Int):Call<Void>
}