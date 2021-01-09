package com.example.novaera.model.api.Remoto

import com.example.novaera.model.api.Local.PDetail
import com.example.novaera.model.api.Local.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiApp {

    @GET("products/")
    suspend fun fetchAllProducts(): Response<List<Product>>

    @GET("details/{id}")
    suspend fun fetchOneProducts(@Path("id") id: Int): Response<PDetail>

}