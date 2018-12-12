package com.example.prime.testapplication.network

import com.google.gson.annotations.SerializedName

class IPModel {

    @SerializedName("query")
    var query: String? = null

    @SerializedName("ip")
    var ip: String? = null
}