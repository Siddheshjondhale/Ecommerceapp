package com.example.ecommerceapp.dataCLass

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers



interface EcommerceApi {

    @GET("/rest/V1/productdetails/6701/253620?lang=en&store=KWD")
    suspend fun getData(): retrofit2.Response<EcommereList>

}
