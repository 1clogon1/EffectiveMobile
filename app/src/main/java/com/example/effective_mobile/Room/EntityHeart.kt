package com.example.effective_mobile.Room

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Heart",indices = [Index(value = ["productId"], unique = true)])
data class EntityHeart(
    @PrimaryKey(autoGenerate = true)
    var id:Int ?= null,
    var productId:String,
)
