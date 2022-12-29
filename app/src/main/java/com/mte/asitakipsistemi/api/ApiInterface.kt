package com.mte.asitakipsistemi.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("mtalhaergun/AsilarData/main/asilar.json")
    fun getData(): Call<List<MyDataItem>>
}