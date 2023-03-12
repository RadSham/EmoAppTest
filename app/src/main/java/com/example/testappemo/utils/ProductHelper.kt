package com.example.testappemo.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.testappemo.model.Product
import org.json.JSONException
import org.json.JSONObject
import java.net.URL
import java.nio.charset.Charset
import java.util.*


object ProductHelper {

    fun getAllProducts(arrayName: String, urlString: String): ArrayList<Product> {
        val productsList = ArrayList<Product>()
        try {
            val url = URL(urlString)
            val jo = parse(url.readText(Charset.defaultCharset()))
            if (jo != null) {
                val array = jo.getJSONArray(arrayName.lowercase(Locale.ROOT))
                for (i in 0 until array.length()) {
                    val jsonObject = array.getJSONObject(i)
                    val product = Product(
                        jsonObject["category"] as String,
                        jsonObject["name"] as String,
                        jsonObject["price"].toString(),
                        getImage(jsonObject["image_url"] as String)
                    )

                    productsList.add(product)
                }
            }
        } catch (e: Exception) {
            println(e)
        }
        return productsList
    }

    private fun parse(json: String): JSONObject? {
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(json)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonObject
    }

    //https://www.geeksforgeeks.org/how-to-load-any-image-from-url-without-using-any-dependency-in-android/
    fun getImage(imageURL: String): Bitmap? {
        var image: Bitmap? = null
            // Tries to get the image and post it in the ImageView
            // with the help of Handler
            try {
                val `in` = URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
            }
            // If the URL doesnot point to
            // image or any other kind of failure
            catch (e: Exception) {
                e.printStackTrace()
            }
        return image
    }


}

