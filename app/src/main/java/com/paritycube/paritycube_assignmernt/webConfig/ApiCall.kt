package com.infinx.saprfid.Web

import com.paritycube.paritycube_assignmernt.home.model.DealsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {

    @GET("top.json")
    fun getTopDeals(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Call<DealsModel>

    @GET("popular.json")
    fun getPopularDeals(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Call<DealsModel>

    @GET("featured.json")
    fun getFeaturedDeals(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Call<DealsModel>

}
