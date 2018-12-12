package com.example.prime.testapplication.network

import io.reactivex.Observable
import retrofit2.http.*
import java.util.HashMap


interface URLService {

    @Headers(
            "Content-Type: application/json",
            "cache-control: no-cache"
    )
    @POST("/getLink")
    fun checkUrl(@Body body: HashMap<String, Any>) : Observable<URLModel>
}

