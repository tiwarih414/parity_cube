package com.paritycube.paritycube_assignmernt.home.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paritycube.paritycube_assignmernt.R
import com.paritycube.paritycube_assignmernt.Util.Utilities
import com.paritycube.paritycube_assignmernt.home.model.DealsModel
import com.paritycube.paritycube_assignmernt.home.repository.DealsRepository

class PopularDealsViewModel(application: Application, perPage: Int, pageCount: Int) : ViewModel() {

    private var popularListData: MutableLiveData<DealsModel>? = null
    private lateinit var dealsRepository: DealsRepository
    var utilities: Utilities = Utilities(application)
    var showError = MutableLiveData<String>()

    init {
        getPopularDeals(application, perPage, pageCount)
    }

    private fun getPopularDeals(application: Application, perPage: Int, pageCount: Int) {
        if (popularListData != null) {
            return
        }
        if (utilities.haveNetworkConnection(application)) {
            dealsRepository = DealsRepository()
            popularListData = dealsRepository.getPopularDeals(perPage, pageCount)

        } else {
            showError.value = application.getString(R.string.no_internet)
        }
    }

    fun getPopularDealsRepository(): LiveData<DealsModel>? {
        return popularListData
    }
}