package com.example.prime.testapplication.network

import io.reactivex.Observable
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface IPService {

    @GET("/json")
    fun getIpAddress() :Observable<IPModel>
}

