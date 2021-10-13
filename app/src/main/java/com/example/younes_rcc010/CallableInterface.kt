package com.example.younes_rcc010

import retrofit2.Call
import retrofit2.http.GET

interface CallableInterface {

    @GET("/v2/top-headlines?country=eg&category=general&apiKey=84d22f84d0e547dab17f0bff8591fc31")
    fun getEg (): Call<NewsModel>
    @GET("/v2/top-headlines?country=de&category=general&apiKey=84d22f84d0e547dab17f0bff8591fc31")
    fun getDe (): Call<NewsModel>
    @GET("/v2/top-headlines?country=us&category=general&apiKey=84d22f84d0e547dab17f0bff8591fc31")
    fun getUs (): Call<NewsModel>
    @GET("/v2/top-headlines?country=it&category=general&apiKey=84d22f84d0e547dab17f0bff8591fc31")
    fun getIt(): Call<NewsModel>

}