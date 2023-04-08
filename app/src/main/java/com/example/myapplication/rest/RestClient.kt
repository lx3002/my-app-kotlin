package com.example.myapplication.rest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object RestClient {
    // variable references to the BASE_URL and retrofit instances
    private val BASE_URL = "https://api.stackexchange.com"  // base url , replace with API used.
    private var mRetrofit: Retrofit? = null  //reference to retrofit
    //building the retrofit instance
    val client : Retrofit
        get() {
            if (mRetrofit == null){
                mRetrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return this.mRetrofit!!
        }
}