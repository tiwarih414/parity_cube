package com.paritycube.paritycube_assignmernt.home.repository

import androidx.lifecycle.MutableLiveData
import com.paritycube.paritycube_assignmernt.home.model.DealsModel
import com.paritycube.paritycube_assignmernt.webConfig.RestService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DealsRepository {

    private var restService = RestService()
    var topDealsListData: MutableLiveData<DealsModel> = MutableLiveData()
    var popularDealsListData: MutableLiveData<DealsModel> = MutableLiveData()
    var featuredDealsListData: MutableLiveData<DealsModel> = MutableLiveData()

    fun getTopDeals(perPage: Int, pageCount: Int) {

        restService.service?.getTopDeals(perPage, pageCount)
            ?.enqueue(object : Callback<DealsModel> {
                override fun onResponse(call: Call<DealsModel>, response: Response<DealsModel>) {
                    if (response.isSuccessful) {
                        topDealsListData.value = response.body()
                    }
                }

                override fun onFailure(call: Call<DealsModel>, t: Throwable) {
                    topDealsListData.value = null
                }

            })
    }

    fun getPopularDeals(perPage: Int, pageCount: Int) {
        restService.service?.getPopularDeals(perPage, pageCount)
            ?.enqueue(object : Callback<DealsModel> {
                override fun onResponse(call: Call<DealsModel>, response: Response<DealsModel>) {
                    if (response.isSuccessful) {
                        popularDealsListData.value = response.body()
                    }
                }

                override fun onFailure(call: Call<DealsModel>, t: Throwable) {
                    popularDealsListData.value = null
                }

            })
    }

    fun getFeaturedDeals(perPage: Int, pageCount: Int) {
        restService.service?.getFeaturedDeals(perPage, pageCount)
            ?.enqueue(object : Callback<DealsModel> {
                override fun onResponse(call: Call<DealsModel>, response: Response<DealsModel>) {
                    if (response.isSuccessful) {
                        featuredDealsListData.value = response.body()
                    }
                }

                override fun onFailure(call: Call<DealsModel>, t: Throwable) {
                    featuredDealsListData.value = null
                }

            })
    }

}