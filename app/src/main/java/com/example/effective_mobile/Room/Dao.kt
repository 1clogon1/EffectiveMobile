package com.example.effective_mobile.Room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insetrItem(eh: EntityHeart)


    @Query("INSERT INTO Heart (id, productid) VALUES (null, :productid);")
    fun insertItem(productid: String)

    @Query("SELECT * FROM Heart")
    fun getAllItem(): Flow<List<EntityHeart>>


    @Query("DELETE FROM Heart WHERE productid = :productid")
    fun deleteProductHeart(productid: String)

    @Query("SELECT * FROM Heart WHERE productid = :productid")
    fun searchHeart(productid: String): Flow<List<EntityHeart>>
}