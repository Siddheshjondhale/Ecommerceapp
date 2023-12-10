package com.example.ecommerceapp

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceapp.Adapters.CircleAvatarAdapter
import com.example.ecommerceapp.Adapters.imageAvatarList
import com.example.ecommerceapp.ViewPageAdapter.ImageList
import com.example.ecommerceapp.ViewPageAdapter.ImageViewCarouselAdapter
import com.example.ecommerceapp.dataCLass.EcommerceApi
import com.example.ecommerceapp.dataCLass.EcommereList
import com.example.ecommerceapp.dataCLass.RetrofitHelper
import com.example.ecommerceapp.databinding.FragmentProductScreenBinding
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.relex.circleindicator.CircleIndicator3
import org.json.JSONException
import org.json.JSONObject
import java.text.DecimalFormat

class ProductScreenFragment : Fragment() {

    companion object {
        fun newInstance() = ProductScreenFragment()
    }

    private var _binding: FragmentProductScreenBinding? = null
    private val binding get() = _binding!!

    private var imageList = mutableListOf<ImageList>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageavatarList = mutableListOf<imageAvatarList>()


        val DataResponse = RetrofitHelper.getInstance().create(EcommerceApi::class.java)

        GlobalScope.launch {
            val result = DataResponse.getData()

            if (result != null) {
                Log.d("DataRetro", result.body().toString())
            }

            val jsonString = result.body().toString()

            try {
                val data: EcommereList = result.body()!!

                // Access the brand_name attribute
                val brandName = data.data.brand_name
                val finalprice = data.data.final_price
                val sku = data.data.sku
                val formattedFinalPrice = formatFinalPrice(finalprice!!)

                val descriptionHtml = data.data.description
                val formattedDescription = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    HtmlCompat.fromHtml(descriptionHtml, HtmlCompat.FROM_HTML_MODE_LEGACY)
                } else {
                    Html.fromHtml(descriptionHtml)
                }

//to get images[0] index image
                imageList.addAll(data.data.configurable_option
                    .flatMap { it.attributes }
                    .mapNotNull { it.images.firstOrNull() as? String }
                    .map { ImageList(it) }
                )
             imageavatarList.addAll(data.data.configurable_option.flatMap { it.attributes }.map { it.images.firstOrNull() as? String }.map { imageAvatarList(
                 it.toString()
             ) })


                withContext(Dispatchers.Main) {
                    binding.productNameTV.text = brandName
                    binding.productPrice.text = formattedFinalPrice + " KWD"
                    binding.productSKU.text = "SKU: $sku"
                    binding.descriptionProduct.text = formattedDescription
                    Log.d("productdescriptio", formattedDescription.toString())

                    binding.carouselViewPager.adapter = ImageViewCarouselAdapter(imageList, binding.carouselViewPager)
                    binding.carouselViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

                    binding.indicator.setViewPager(binding.carouselViewPager)
//                    binding.indicator.setOrientation(CircleIndicator3.HORIZONTAL)
//                    binding.indicator.createIndicators(imageList.size, 0)



//                    avatar recyler view
                    binding.circleAvatarRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    binding.circleAvatarRecyclerview.adapter = CircleAvatarAdapter(imageavatarList, requireContext())



                }

                Log.d("Imagelistbaba", imageList.toString())


            } catch (e: JsonSyntaxException) {
                Log.e("DataRetro", "Error parsing JSON: ${result.body()?.toString()}")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun formatFinalPrice(finalPrice: String): String {
        val formattedValue = finalPrice.toDouble()
        val decimalFormat = DecimalFormat("#.##")
        return decimalFormat.format(formattedValue)
    }
}
