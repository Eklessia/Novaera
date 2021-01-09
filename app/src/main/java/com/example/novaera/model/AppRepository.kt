package com.example.novaera.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.novaera.model.api.Local.DaoProduct
import com.example.novaera.model.api.Local.PDetail
import com.example.novaera.model.api.Remoto.RetrofitApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class AppRepository(private val myDao: DaoProduct) {

    private val myRetrofit=RetrofitApiClient.retrofitInstance()

     val listProducts= myDao.getAllProducts()


    fun getProductsDetail(id: Int): LiveData<PDetail> {

        return myDao.getOneProductsDetails(id)
    }



    fun getProductsFromApi()= CoroutineScope(Dispatchers.IO).launch{

        val service= kotlin.runCatching { myRetrofit.fetchAllProducts() }
        service.onSuccess {
            when(it.code()) {
                in 200..299 -> it.body()?.let {

                    myDao.insertAllProducts(it)
                }

                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
        }

        service.onFailure {
            Log.e("ERROR", it.message.toString())

        }

    }

    fun getDetailFromApi(id: Int)= CoroutineScope(Dispatchers.IO).launch{

        val service= kotlin.runCatching { myRetrofit.fetchOneProducts(id)}
        service.onSuccess {
            when(it.code()) {
                in 200..299 -> it.body()?.let {
                    myDao.insertOneDetail(it)
                }

                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
        }

        service.onFailure {
            Log.e("ERROR", it.message.toString())

        }

    }




}