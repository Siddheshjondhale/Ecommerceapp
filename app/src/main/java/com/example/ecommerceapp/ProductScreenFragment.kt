package com.example.ecommerceapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ecommerceapp.ViewPageAdapter.ImageList
import com.example.ecommerceapp.ViewPageAdapter.ImageViewCarouselAdapter

import com.example.ecommerceapp.databinding.FragmentProductScreenBinding

class ProductScreenFragment : Fragment() {


    companion object {
        fun newInstance() = ProductScreenFragment()
    }

    private lateinit var viewModel: ProductScreenViewModel
    private var _binding: FragmentProductScreenBinding? = null
    private val binding get() = _binding!!


    private var imageList= mutableListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductScreenBinding.inflate(inflater,container,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val imageList = mutableListOf<ImageList>()

        imageList.add(ImageList(R.drawable.ic_launcher_background))
        imageList.add(ImageList(R.drawable.ic_launcher_background))
        imageList.add(ImageList(R.drawable.ic_launcher_foreground))
        imageList.add(ImageList(R.drawable.ic_launcher_background))

//        binding.carouselViewPager.adapter = HomeScreenCarouselViewPagerAdapter(imageList,binding.carouselViewPager)

        binding.carouselViewPager.adapter=ImageViewCarouselAdapter(imageList,binding.carouselViewPager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // important, clear bindings onDestroyView
    }

}