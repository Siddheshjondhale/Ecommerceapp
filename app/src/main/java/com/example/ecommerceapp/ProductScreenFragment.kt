package com.example.ecommerceapp
// ProductScreenFragment.kt

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceapp.Adapters.CircleAvatarAdapter
import com.example.ecommerceapp.Adapters.imageAvatarList
import com.example.ecommerceapp.ViewPageAdapter.ImageList
import com.example.ecommerceapp.ViewPageAdapter.ImageViewCarouselAdapter
import com.example.ecommerceapp.databinding.FragmentProductScreenBinding

class ProductScreenFragment : Fragment() {

    private var _binding: FragmentProductScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProductScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProductScreenViewModel::class.java)

        observeViewModel()

        viewModel.fetchData()
    }

    private fun observeViewModel() {
        viewModel.productName.observe(viewLifecycleOwner, { brandName ->
            binding.productNameTV.text = brandName
        })

        viewModel.productPrice.observe(viewLifecycleOwner, { formattedFinalPrice ->
            binding.productPrice.text = formattedFinalPrice
        })

        viewModel.productSKU.observe(viewLifecycleOwner, { sku ->
            binding.productSKU.text = sku
        })

        viewModel.productDescription.observe(viewLifecycleOwner, { formattedDescription ->
            binding.descriptionProduct.text = formattedDescription
        })

        viewModel.imageList.observe(viewLifecycleOwner, { imageList ->
            binding.carouselViewPager.adapter =
                ImageViewCarouselAdapter(imageList.toMutableList(), binding.carouselViewPager)
            binding.carouselViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            binding.indicator.setViewPager(binding.carouselViewPager)
        })

        viewModel.imageAvatarList.observe(viewLifecycleOwner, { imageAvatarList ->
            binding.circleAvatarRecyclerview.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.circleAvatarRecyclerview.adapter =
                CircleAvatarAdapter(imageAvatarList.toMutableList(), requireContext())
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

