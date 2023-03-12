package com.example.testappemo.adaptors

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testappemo.R
import com.example.testappemo.databinding.SingleProductBinding
import com.example.testappemo.model.Product

class ProductsRvAdapter : RecyclerView.Adapter<ProductsRvAdapter.ViewHolder>() {
    private val productsArray = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SingleProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(productsArray[position])
    }

    override fun getItemCount(): Int {
        return productsArray.size
    }

    class ViewHolder(val binding: SingleProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(product: Product) {
            binding.apply {
                productCategorySingleProduct.text = product.category
                productNameSingleProduct.text = product.name
                productPriceSingleProduct.text = product.price
                if (product.image==null) productImageSingleProduct.setImageResource(R.drawable.example1)
                else productImageSingleProduct.setImageBitmap(product.image)
            }
        }
    }

    private fun updateAdapter(newList: List<Product>) {
        productsArray.clear()
        productsArray.addAll(newList)
        notifyDataSetChanged()
    }

    fun updateUIAdapter(activity: Activity, list: ArrayList<Product>){
        activity.runOnUiThread{
            kotlin.run {
                updateAdapter(list)
            }
        }
    }


}