package com.example.novaera.model.api.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class,PDetail::class],version=2)
 abstract class DataBase: RoomDatabase() {

    abstract fun daoPap(): DaoProduct

    companion object {


        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(context: Context): DataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "plapux_databaseV1"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}