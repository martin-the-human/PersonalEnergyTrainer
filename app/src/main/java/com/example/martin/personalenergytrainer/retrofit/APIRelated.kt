package com.example.martin.personalenergytrainer.retrofit

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

// code by mohammed on 22/11/17
// https://github.com/Audhil/FlaskWithAndroid

class APIRelated {

    interface APIService{
        @GET("user/{user}")
        fun greetUser(@Path("user") user: String): Call<ResponseBody>

        @Headers("Content-type: application/json")
        @POST("/api/post_some_data")
        fun getVectors(@Body body: JsonObject): Call<ResponseBody>
    }

    companion object{
        private val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.4:5000") //change to our url
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()

        var service = retrofit.create(APIService::class.java)
    }
}
