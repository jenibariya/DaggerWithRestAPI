package com.example.daggerwithrestapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.daggerwithrestapi.adapter.ImagePagerAdapter
import com.example.daggerwithrestapi.apiservice.ProductService
import com.example.daggerwithrestapi.databinding.FragmentProductDetailsBinding
import com.example.daggerwithrestapi.repository.ProductRepository
import com.example.daggerwithrestapi.viewmodel.MainViewModelFactory
import com.example.daggerwithrestapi.viewmodel.ProductViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    private var productName: String? = null
    private var productDesc: String? = null
    private var productPrice: Int? = null
    private var productDiscount: Double? = null
    private var productRating: Double? = null
    private var productStock: Int? = null
    private var productBrand: String? = null
    private var productCat: String? = null
    private lateinit var productImage: List<String>

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        productName = arguments?.getString("productName")
        productDesc = arguments?.getString("productDesc")
        productPrice = arguments?.getInt("productPrice")
        productDiscount = arguments?.getDouble("productDiscount")
        productRating = arguments?.getDouble("productRating")
        productStock = arguments?.getInt("productStock")
        productBrand = arguments?.getString("productBrand")
        productCat = arguments?.getString("productCat")
        productImage = arguments?.getStringArrayList("productImage")!!


        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        binding.progressBar.visibility = View.VISIBLE

        productViewModel.productList.observe(this.requireActivity(), Observer {
            binding.progressBar.visibility = View.GONE
            binding.view.visibility = View.VISIBLE

            binding.productNameTextView.text = productName
            binding.productDescriptionTextView.text = productDesc
            binding.productPriceTextView.text = "₹$productPrice"
            binding.productDiscountTextView.text = "$productDiscount% off"
            binding.productRatingTextView.text = "User review rating $productRating"
            binding.productStockTextView.text = "$productStock items left"
            binding.productBrandTextView.text = productBrand
            binding.productCatTextView.text = "- $productCat"

            val adapter = ImagePagerAdapter(productImage, this.requireContext())
            binding.viewPager.adapter = adapter

            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                binding.tabLayout.getTabAt(position)?.select()
            }.attach()
        })
        return view
    }
}