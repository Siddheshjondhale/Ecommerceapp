package com.example.ecommerceapp.ViewPageAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ItemCarouselBinding

class ImageViewCarouselAdapter(private val imageList: MutableList<ImageList>, private val viewPager2: ViewPager2): RecyclerView.Adapter<ImageViewCarouselAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: ItemCarouselBinding) : RecyclerView.ViewHolder(binding.root){
        val carouselViewPager = itemView.findViewById<ImageView>(R.id.carouselViewPager)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCarouselBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      holder.binding.imageView.setImageResource(imageList[position].imageResourceId)


        //holder.binding.carouselImageView.setImageResource(imageList[position].imageResourceId)

    }

    override fun getItemCount(): Int {
        return imageList.size
    }



}


