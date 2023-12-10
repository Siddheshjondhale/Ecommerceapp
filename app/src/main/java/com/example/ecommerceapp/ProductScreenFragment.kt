package com.example.ecommerceapp
import android.os.Bundle
import android.telephony.TelephonyCallback.DataActivationStateListener
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceapp.Adapters.CircleAvatarAdapter
import com.example.ecommerceapp.Adapters.imageAvatarList

import com.example.ecommerceapp.ViewPageAdapter.ImageList
import com.example.ecommerceapp.ViewPageAdapter.ImageViewCarouselAdapter
import com.example.ecommerceapp.dataCLass.Data
import com.example.ecommerceapp.dataCLass.EcommerceApi
import com.example.ecommerceapp.dataCLass.EcommereList
import com.example.ecommerceapp.dataCLass.RetrofitHelper

import com.example.ecommerceapp.databinding.FragmentProductScreenBinding
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.relex.circleindicator.CircleIndicator3
import java.text.DecimalFormat

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

//        var brandName: String? = null
//
//        var finalprice: String? = null

        val DataResponse=RetrofitHelper.getInstance().create(EcommerceApi::class.java)
        GlobalScope.launch {

            val result=DataResponse.getData()
//            if(result!=null){
//                Log.d("DataRetro",result.body().toString())
//            }

            val jsonString = result.body().toString()

            try {
                val data: EcommereList = result.body()!!

                // Access the brand_name attribute
               val brandName = data.data.brand_name
                val finalprice=data.data.final_price
                val sku =data.data.sku
                val formattedFinalPrice = formatFinalPrice(finalprice!!)
// Inside your GlobalScope.launch block, where you're setting the description text
                val descriptionHtml = data.data.description
                val formattedDescription = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    HtmlCompat.fromHtml(descriptionHtml, HtmlCompat.FROM_HTML_MODE_LEGACY)
                } else {
                    Html.fromHtml(descriptionHtml)
                }

                withContext(Dispatchers.Main) {
                    binding.productNameTV.setText(brandName)
                    binding.productPrice.setText(formattedFinalPrice+" KWD")
                    binding.productSKU.setText("SKU: "+sku)
                    binding.descriptionProduct.setText(formattedDescription)
              Log.d("productdescriptio",formattedDescription.toString())

                }

                // Log the brand name
                Log.d("BrandName", "Brand Name: $brandName")
            } catch (e: JsonSyntaxException) {
                // Handle the case where the response is not a valid JSON object
                Log.e("DataRetro", "Error parsing JSON: ${result.body()?.toString()}")
            }
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // important, clear bindings onDestroyView
    }
    private fun formatFinalPrice(finalPrice: String): String {
        // Convert finalPrice to Double to remove trailing zeros
        val formattedValue = finalPrice.toDouble()
        // Use DecimalFormat to format the value
        val decimalFormat = DecimalFormat("#.##")
        return decimalFormat.format(formattedValue)
    }


}

