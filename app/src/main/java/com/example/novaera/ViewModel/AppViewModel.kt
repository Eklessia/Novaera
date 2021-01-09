package com.example.novaera.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.novaera.model.AppRepository
import com.example.novaera.model.api.Local.DataBase
import com.example.novaera.model.api.Local.PDetail
import com.example.novaera.model.api.Local.Product

class AppViewModel(application: Application):AndroidViewModel(application) {

    private val myRepository:AppRepository

    val election= MutableLiveData<Int>()
    val viewListProducts:LiveData <List<Product>>


    init {
        val nDao= DataBase.getDatabase(application).daoPap()
        myRepository=  AppRepository(nDao)

        viewListProducts=myRepository.listProducts
        myRepository.getProductsFromApi()
    }


    fun getProductDetail(id: Int): LiveData<PDetail>{
        myRepository.getDetailFromApi(id)
        return myRepository.getProductsDetail(id)

    }

    fun  ProductDetailSelect  (id: Int){
        election.value=id
    }






}