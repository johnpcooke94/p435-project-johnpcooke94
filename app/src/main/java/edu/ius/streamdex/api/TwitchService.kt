package edu.ius.streamdex.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitchService {
    @GET("streams/")
    fun getStream(@Query("user_login")userLogin: String?): Call<StreamResponse>

    @GET("users/")
    fun getUser(@Query("user_login")userLogin: String?): Call<StreamerResponse>
}