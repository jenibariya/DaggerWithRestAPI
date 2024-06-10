package com.example.daggerwithrestapi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggerwithrestapi.R
import com.example.daggerwithrestapi.adapter.ProductAdapter
import com.example.daggerwithrestapi.custom.SwipeToDeleteCallback
import com.example.daggerwithrestapi.databinding.FragmentProductListBinding
import com.example.daggerwithrestapi.dataclass.ProductList
import com.example.daggerwithrestapi.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment(), ProductAdapter.OnProductClickListener {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private lateinit var productViewModel: ProductViewModel
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        val view = binding.root

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        binding.progressBar.visibility = View.VISIBLE

        binding.productListRv.layoutManager = LinearLayoutManager(this.requireContext())

        productViewModel.productList.observe(this.requireActivity(), Observer {
            binding.progressBar.visibility = View.GONE

            productAdapter =
                ProductAdapter(it as MutableList<ProductList.Product>, this.requireContext(), this)
            binding.productListRv.adapter = productAdapter

            val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(productAdapter))
            itemTouchHelper.attachToRecyclerView(binding.productListRv)

        })

        return view
    }

    override fun onProductClicked(product: ProductList.Product) {
        val bundle = bundleOf(
            "productName" to product.title,
            "productDesc" to product.description,
            "productPrice" to product.price,
            "productDiscount" to product.discountPercentage,
            "productRating" to product.rating,
            "productStock" to product.stock,
            "productBrand" to product.brand,
            "productCat" to product.category,
            "productImage" to product.images
        )

        findNavController().navigate(R.id.action_productListFragment_to_detailsFragment, bundle)
    }
}