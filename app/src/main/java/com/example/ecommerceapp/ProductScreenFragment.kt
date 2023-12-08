package com.example.ecommerceapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ecommerceapp.ViewPageAdapter.ImageList
import com.example.ecommerceapp.ViewPageAdapter.ImageViewCarouselAdapter

class ProductScreenFragment : Fragment() {


    companion object {
        fun newInstance() = ProductScreenFragment()
    }

    private lateinit var viewModel: ProductScreenViewModel
    private var _binding: ProductScreenFragment? = null
    private val binding get() = _binding!!


    private var imageList= mutableListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val imageList = mutableListOf<ImageList>()

        imageList.add(ImageList(R.drawable.ic_launcher_background))
        imageList.add(ImageList(com.google.android.material.R.drawable.ic_clear_black_24))
        imageList.add(ImageList(com.google.android.material.R.drawable.ic_keyboard_black_24dp))
        imageList.add(ImageList(R.drawable.ic_launcher_background))

        binding.carouselViewPager.adapter = HomeScreenCarouselViewPagerAdapter(imageList,binding.carouselViewPager)

        binding.carouselViewPager.adapter=ImageViewCarouselAdapter(imageList,binding.carouselViewPager)

        return inflater.inflate(R.layout.fragment_product_screen, container, false)
    }


}