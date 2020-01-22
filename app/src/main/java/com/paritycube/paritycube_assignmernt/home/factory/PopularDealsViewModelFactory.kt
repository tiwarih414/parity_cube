package com.paritycube.paritycube_assignmernt.home.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paritycube.paritycube_assignmernt.home.viewModel.PopularDealsViewModel

class PopularDealsViewModelFactory(
    var application: Application,
    var perPage: Int,
    var pageCount: Int
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularDealsViewModel(application, perPage, pageCount) as T
    }

}