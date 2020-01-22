package com.paritycube.paritycube_assignmernt.home.repository

import androidx.lifecycle.MutableLiveData
import com.paritycube.paritycube_assignmernt.home.model.DealsModel
import com.paritycube.paritycube_assignmernt.webConfig.RestService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DealsRepository {

    private var restService = RestService()
    var dealsListData: MutableLiveData<DealsModel> = MutableLiveData()

    fun getTopDeals(perPage: Int, pageCount: Int): MutableLiveData<DealsModel> {

        restService.service?.getTopDeals(perPage, pageCount)
            ?.enqueue(object : Callback<DealsModel> {
                override fun onResponse(call: Call<DealsModel>, response: Response<DealsModel>) {
                    if (response.isSuccessful) {
                        dealsListData.value = response.body()
                    }
                }

                override fun onFailure(call: Call<DealsModel>, t: Throwable) {
                    dealsListData.value = null
                }

            })
        return dealsListData
    }

    fun getPopularDeals(perPage: Int, pageCount: Int): MutableLiveData<DealsModel> {
        restService.service?.getPopularDeals(perPage, pageCount)
            ?.enqueue(object : Callback<DealsModel> {
                override fun onResponse(call: Call<DealsModel>, response: Response<DealsModel>) {
                    if (response.isSuccessful) {
                        dealsListData.value = response.body()
                    }
                }

                override fun onFailure(call: Call<DealsModel>, t: Throwable) {
                    dealsListData.value = null
                }

            })
        return dealsListData
    }

    fun getFeaturedDeals(perPage: Int, pageCount: Int): MutableLiveData<DealsModel> {
        restService.service?.getFeaturedDeals(perPage, pageCount)
            ?.enqueue(object : Callback<DealsModel> {
                override fun onResponse(call: Call<DealsModel>, response: Response<DealsModel>) {
                    if (response.isSuccessful) {
                        dealsListData.value = response.body()
                    }
                }

                override fun onFailure(call: Call<DealsModel>, t: Throwable) {
                    dealsListData.value = null
                }

            })
        return dealsListData
    }

}