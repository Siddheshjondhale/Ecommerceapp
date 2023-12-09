package com.example.ecommerceapp
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceapp.Adapters.CircleAvatarAdapter
import com.example.ecommerceapp.Adapters.imageAvatarList

import com.example.ecommerceapp.ViewPageAdapter.ImageList
import com.example.ecommerceapp.ViewPageAdapter.ImageViewCarouselAdapter

import com.example.ecommerceapp.databinding.FragmentProductScreenBinding
import me.relex.circleindicator.CircleIndicator3

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
        val imageavatarList = mutableListOf<imageAvatarList>()

        imageList.add(ImageList(R.drawable.word))
        imageList.add(ImageList(R.drawable.ic_launcher_background))
        imageList.add(ImageList(R.drawable.ic_launcher_foreground))
        imageList.add(ImageList(R.drawable.ic_launcher_background))



        binding.carouselViewPager.adapter=ImageViewCarouselAdapter(imageList,binding.carouselViewPager)
        binding.carouselViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.indicator.setViewPager(binding.carouselViewPager)

        // Optional: Customize indicator behavior
        binding.indicator.setOrientation(CircleIndicator3.HORIZONTAL)
        binding.indicator.createIndicators(imageList.size, 0)


        imageavatarList.add(imageAvatarList("https://klinq.com//media//catalog//product//8//8//8809579837961-1_1pmzzkspggjyzljy.jpg"))
        imageavatarList.add(imageAvatarList("https://klinq.com//media//catalog//product//8//8//8809579837961-1_1pmzzkspggjyzljy.jpg"))
        imageavatarList.add(imageAvatarList("https://klinq.com//media//catalog//product//8//8//8809579837961-1_1pmzzkspggjyzljy.jpg"))
        imageavatarList.add(imageAvatarList("https://klinq.com//media//catalog//product//8//8//8809579837961-1_1pmzzkspggjyzljy.jpg"))
        imageavatarList.add(imageAvatarList("https://klinq.com//media//catalog//product//8//8//8809579837961-1_1pmzzkspggjyzljy.jpg"))
        imageavatarList.add(imageAvatarList("https://klinq.com//media//catalog//product//8//8//8809579837961-1_1pmzzkspggjyzljy.jpg"))
        imageavatarList.add(imageAvatarList("https://klinq.com//media//catalog//product//8//8//8809579837961-1_1pmzzkspggjyzljy.jpg"))
        imageavatarList.add(imageAvatarList("https://klinq.com//media//catalog//product//8//8//8809579837961-1_1pmzzkspggjyzljy.jpg"))
        imageavatarList.add(imageAvatarList("https://klinq.com//media//catalog//product//8//8//8809579837961-1_1pmzzkspggjyzljy.jpg"))

        // circleAvatar

        binding.circleAvatarRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.circleAvatarRecyclerview.adapter = CircleAvatarAdapter(imageavatarList, requireContext())



    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // important, clear bindings onDestroyView
    }

}

