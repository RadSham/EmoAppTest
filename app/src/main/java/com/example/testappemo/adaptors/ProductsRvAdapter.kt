package com.example.testappemo.adaptors

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testappemo.databinding.SingleProductBinding
import com.example.testappemo.model.Product

class ProductsRvAdapter : RecyclerView.Adapter<ProductsRvAdapter.ViewHolder>() {
    private val productsArray = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SingleProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("MyLog", "onCreateViewHolder")
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
            }
        }
    }

    fun updateAdapter(newList: List<Product>) {
        productsArray.addAll(newList)
        notifyDataSetChanged()
    }


}