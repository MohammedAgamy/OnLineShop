package com.example.onlineshop.model

data class ItemsModel(
    var title:String= "",
    var descravtion:String= "",
    var picUrl:ArrayList<String> = ArrayList(),
    var size:ArrayList<String> = ArrayList(),
    var price:Double= 0.0,
    var rating:Double= 0.0,
    var numberInCAr:Int= 0

)
