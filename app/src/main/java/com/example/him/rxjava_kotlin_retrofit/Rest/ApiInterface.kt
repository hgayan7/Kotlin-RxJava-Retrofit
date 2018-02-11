package com.example.him.rxjava_kotlin_retrofit.Rest

import com.example.him.rxjava_kotlin_retrofit.Model.UserData
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by him on 2/11/2018.
 */
interface ApiInterface {

    @GET("users")
    fun getUserData():Observable<List<UserData>>

}