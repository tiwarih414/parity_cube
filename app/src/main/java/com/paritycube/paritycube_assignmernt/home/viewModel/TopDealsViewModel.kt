package com.paritycube.paritycube_assignmernt.home.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paritycube.paritycube_assignmernt.R
import com.paritycube.paritycube_assignmernt.Util.Utilities
import com.paritycube.paritycube_assignmernt.home.model.DealsModel
import com.paritycube.paritycube_assignmernt.home.repository.DealsRepository

class TopDealsViewModel(val application: Application, perPage: Int, pageCount: Int) : ViewModel() {

    private var topListData: MutableLiveData<DealsModel>? = null
    private var dealsRepository: DealsRepository = DealsRepository()
    var utilities: Utilities = Utilities(application)
    var showError = MutableLiveData<String>()

    init {
        topListData = dealsRepository.topDealsListData
        getTopDeals(perPage, pageCount)
    }

    fun getTopDeals( perPage: Int, pageCount: Int) {
        if (utilities.haveNetworkConnection(application)) {
           dealsRepository.getTopDeals(perPage, pageCount)

        } else {
            showError.value = application.getString(R.string.no_internet)
        }
    }

    fun getTopDealsRepository(): LiveData<DealsModel>? {
        return topListData
    }
}