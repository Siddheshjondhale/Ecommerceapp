package com.example.ecommerceapp.Adapters
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.ViewPageAdapter.ImageList

import java.util.*
class CircleAvatarAdapter(private val imageUrls: MutableList<imageAvatarList>, private val context: Context) :
    RecyclerView.Adapter<CircleAvatarAdapter.CircleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_avatarcolor, parent, false)
        return CircleViewHolder(view)
    }

    override fun onBindViewHolder(holder: CircleViewHolder, position: Int) {
        val imageItem = imageUrls[position]
//        val linktopaste=imageItem.imageResourceId.toString()



        Log.d("testbhai", imageItem.imageResourceId.toString())
        holder.bind(imageItem)
    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }

    inner class CircleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val avatarImageView: ImageView = itemView.findViewById(R.id.circularImageView)

        fun bind(imageItem: imageAvatarList) {
            // Use Glide to load the image into the ImageView
            Glide.with(context)
                .load(imageItem.imageResourceId) // Assuming imageResourceId is a valid resource ID
                .into(avatarImageView)
        }
    }
}
