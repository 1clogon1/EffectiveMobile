package com.example.effective_mobile.Model.Product

data class ItemsProductModel(
    var id: String,
    var title:String,
    var subtitle:String,
    var price:PriceModel,
    var feedback: FeedbackModel,
    var tags:List<String>,
    var available:Int,
    var description:String,
    var info: List<InfoModel>,
    var ingredients:String,
    var status: Boolean = false
)
