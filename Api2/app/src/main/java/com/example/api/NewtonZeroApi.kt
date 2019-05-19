package com.example.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NewtonZeroAPI {
    @GET("/{operation}/{expression}")
    fun resolve(@Path("operation") operation : String,@Path("expression") expression : String)  : Call<NewtonZeroCallBack>
}