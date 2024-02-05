package com.example.effective_mobile.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [EntityHeart::class],
    version = 1
)
abstract class ProductDB:RoomDatabase() {
    abstract fun getDao():Dao

    companion object{
        fun getProduct(context: Context):ProductDB{
            return Room.databaseBuilder(context.applicationContext,ProductDB::class.java,"heartproduct.db").build()
        }
    }
}