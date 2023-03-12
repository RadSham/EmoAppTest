package com.example.testappemo.model

import android.graphics.Bitmap

data class Product(
    var category:String = "ProductCategory",
    var name:String = "ProductName",
    var price:String = "ProductPrice",
    var image:Bitmap? = null
    )
