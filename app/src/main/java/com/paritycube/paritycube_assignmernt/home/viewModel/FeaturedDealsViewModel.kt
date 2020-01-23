package com.paritycube.paritycube_assignmernt.home.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paritycube.paritycube_assignmernt.R
import com.paritycube.paritycube_assignmernt.Util.Utilities
import com.paritycube.paritycube_assignmernt.home.model.DealsModel
import com.paritycube.paritycube_assignmernt.home.repository.DealsRepository

class FeaturedDealsViewModel(val application: Application, perPage: Int, pageCount: Int) :
    ViewModel() {

    private var featuredListData: MutableLiveData<DealsModel>? = null
    private var dealsRepository: DealsRepository = DealsRepository()
    private var utilities: Utilities = Utilities(application)
    var showError = MutableLiveData<String>()

    init {
        featuredListData = dealsRepository.featuredDealsListData
        getFeaturedDeals(perPage, pageCount)
    }

    fun getFeaturedDeals(perPage: Int, pageCount: Int) {
        if (utilities.haveNetworkConnection(application)) {
            dealsRepository.getFeaturedDeals(perPage, pageCount)

        } else {
            showError.value = application.getString(R.string.no_internet)
        }
    }

    fun getFeaturedDealsRepository(): LiveData<DealsModel>? {
        return featuredListData
    }

}