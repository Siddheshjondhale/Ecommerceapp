package com.example.ecommerceapp

import android.text.Html
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModel


import androidx.lifecycle.MutableLiveData

import com.example.ecommerceapp.Adapters.imageAvatarList
import com.example.ecommerceapp.ViewPageAdapter.ImageList
import com.example.ecommerceapp.dataCLass.EcommerceApi
import com.example.ecommerceapp.dataCLass.EcommereList
import com.example.ecommerceapp.dataCLass.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DecimalFormat

class ProductScreenViewModel : ViewModel() {

    val productName = MutableLiveData<String>()
    val productPrice = MutableLiveData<String>()
    val productSKU = MutableLiveData<String>()
    val productDescription = MutableLiveData<String>()
    val imageList = MutableLiveData<List<ImageList>>()
    val imageAvatarList = MutableLiveData<List<imageAvatarList>>()

    fun fetchData() {
        GlobalScope.launch {
            val result = RetrofitHelper.getInstance().create(EcommerceApi::class.java).getData()

            if (result != null && result.isSuccessful) {
                val data: EcommereList? = result.body()

                data?.let {
                    val brandName = it.data.brand_name
                    val finalprice = it.data.final_price
                    val sku = it.data.sku
                    val formattedFinalPrice = formatFinalPrice(finalprice!!)

                    val descriptionHtml = it.data.description
                    val formattedDescription = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        HtmlCompat.fromHtml(descriptionHtml, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                    } else {
                        Html.fromHtml(descriptionHtml).toString()
                    }

                    val imageListData = it.data.configurable_option
                        .flatMap { it.attributes }
                        .mapNotNull { it.images.firstOrNull() as? String }
                        .map { ImageList(it) }

                    val imageAvatarListData = it.data.configurable_option.flatMap { it.attributes }
                        .map { it.images.firstOrNull() as? String }
                        .map { imageAvatarList(it.toString()) }

                    withContext(Dispatchers.Main) {
                        productName.value = brandName
                        productPrice.value = formattedFinalPrice + " KWD"
                        productSKU.value = "SKU: $sku"
                        productDescription.value = formattedDescription
                        imageList.value = imageListData
                        imageAvatarList.value = imageAvatarListData
                    }
                }
            }
        }
    }

    private fun formatFinalPrice(finalPrice: String): String {
        val formattedValue = finalPrice.toDouble()
        val decimalFormat = DecimalFormat("#.##")
        return decimalFormat.format(formattedValue)
    }
}
