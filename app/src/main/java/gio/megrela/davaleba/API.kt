package gio.megrela.davaleba

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface API {

    companion object {
        fun create(): API = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API::class.java)
    }

    @GET("users")
    fun getUsers(): Call<UserList>

    @GET("users/{id}")
    fun getUserById(@Path("id") id: Int): Call<UserResponse>

    @POST("users")
    fun createUser(@Body request: CreateUserRequest): Call<CreateUserResponse>
}