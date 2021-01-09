package com.example.novaera.model.api.Remoto

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApiClient {


    companion object{

        private const val BASE_URL= " https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"


        fun retrofitInstance(): ApiApp {
            val retrofitClient = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofitClient.create(ApiApp::class.java)
        }

    }
}