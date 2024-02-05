package com.example.effective_mobile.Model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.effective_mobile.Model.Product.HeartModel
import com.example.effective_mobile.Model.Product.ItemsProductModel
import com.example.effective_mobile.Model.Product.ProductModel

class ViewModel: ViewModel() {
    val filter = MutableLiveData<List<FilterModel>>()

    val product = MutableLiveData<List<ItemsProductModel>>()

    val heartId = MutableLiveData<String>()

    val heartList = MutableLiveData<List<ItemsProductModel>>()


    val productHeart = MutableLiveData<List<ItemsProductModel>>()
    val heartList2 = MutableLiveData<List<ItemsProductModel>>()


    val productCard = MutableLiveData<ItemsProductModel>()
}