package com.example.him.rxjava_kotlin_retrofit.Rest

import com.example.him.rxjava_kotlin_retrofit.Model.UserData
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by him on 2/11/2018.
 */
class ApiClient {

    //base url ends with '/'
    val BASE_URL="https://api.github.com/"
    val client:ApiInterface

    init {

        val retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        client=retrofit.create<ApiInterface>(ApiInterface::class.java)

    }

    fun loadUserData():Observable<List<UserData>>{

        return client.getUserData()

    }
}