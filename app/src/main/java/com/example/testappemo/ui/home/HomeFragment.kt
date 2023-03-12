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
import com.example.testappemo.utils.ProductHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val adapter = ProductsRvAdapter()
    val adapter2 = ProductsRvAdapter()
    val adapter3 = ProductsRvAdapter()
    private var job: Job? = null

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
        updateAdaptors()
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

    }

    fun updateAdaptors(){
        job = CoroutineScope(Dispatchers.Default).launch {
            val listLatest = ProductHelper.getAllProducts("latest", "https://run.mocky.io/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
            val listFlashSale = ProductHelper.getAllProducts("flash_sale", "https://run.mocky.io/v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
            adapter.updateUIAdapter(requireActivity(), listLatest)
            adapter2.updateUIAdapter(requireActivity(), listFlashSale)
            adapter3.updateUIAdapter(requireActivity(), arrayListOf(Product(), Product(), Product(), Product()))
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        job?.cancel()
    }
}