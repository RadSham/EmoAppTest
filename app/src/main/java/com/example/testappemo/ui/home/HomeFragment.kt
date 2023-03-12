package com.example.testappemo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testappemo.R
import com.example.testappemo.adaptors.ProductsRvAdapter
import com.example.testappemo.databinding.FragmentHomeBinding
import com.example.testappemo.model.Product

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val adapter = ProductsRvAdapter()
    val adapter2 = ProductsRvAdapter()
    val adapter3 = ProductsRvAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //set menu at toolbar
        binding.layoutToolbar.tbShop.inflateMenu(R.menu.shop_tb_menu)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rcViewLatest.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            rcViewFlashSale.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            rcViewBrands.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            rcViewLatest.adapter = adapter
            rcViewFlashSale.adapter = adapter2
            rcViewBrands.adapter = adapter3
        }
        adapter.updateAdapter(listOf(Product(), Product(), Product(), Product(), Product(), Product(), Product()))
        adapter2.updateAdapter(listOf(Product(), Product(), Product()))
        adapter3.updateAdapter(listOf(Product(), Product(), Product(), Product()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}