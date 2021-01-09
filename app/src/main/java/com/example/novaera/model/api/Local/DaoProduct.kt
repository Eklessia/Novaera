package com.example.novaera.model.api.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface DaoProduct {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(list: List<Product>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneDetail(details: PDetail)

    @Query("SELECT * FROM product")
    fun getAllProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM pDetail WHERE id=:id")
    fun getOneProductsDetails(id: Int): LiveData<PDetail>



}