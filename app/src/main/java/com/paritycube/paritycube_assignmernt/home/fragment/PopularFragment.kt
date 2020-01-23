package com.paritycube.paritycube_assignmernt.home.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paritycube.paritycube_assignmernt.R
import com.paritycube.paritycube_assignmernt.home.adapter.RecylerDataAdapter
import com.paritycube.paritycube_assignmernt.home.factory.PopularDealsViewModelFactory
import com.paritycube.paritycube_assignmernt.home.model.DealsModel
import com.paritycube.paritycube_assignmernt.home.viewModel.PopularDealsViewModel
import kotlinx.android.synthetic.main.fragment_popular.*
import kotlinx.android.synthetic.main.fragment_top.*

/**
 * A simple [Fragment] subclass.
 */
class PopularFragment : Fragment() {

    lateinit var popularDealsViewModel: PopularDealsViewModel
    lateinit var popularDealsArrlist: ArrayList<DealsModel.Datum>

    var recylerDataAdapter: RecylerDataAdapter? = null
    lateinit var layoutManager: RecyclerView.LayoutManager

    var pageCount = 1
    var perPage = 10
    var isLoading = true
    var visibleItemCount: Int? = null
    var totalItemCount: Int? = null
    var firstVisibleItemPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        popularDealsArrlist = ArrayList()
        popularDealsViewModel = ViewModelProviders.of(
            activity!!, PopularDealsViewModelFactory(activity!!.application, 10, 1)
        ).get(PopularDealsViewModel::class.java)
        popularDealsViewModel.showError.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

        getPopularDeals()

        layoutManager = LinearLayoutManager(activity!!)
        recycler_popular.layoutManager = layoutManager
        recycler_popular.addOnScrollListener(recyclerViewOnScrollListner)
    }

    private fun getPopularDeals() {
        popularDealsViewModel.getPopularDealsRepository()?.observe(this, Observer {
            if (it != null) {
                if (pageCount == 1) {
                    showFreshList(it)
                } else {
                    showAppendedList(it)
                }
            } else {
                Log.d("Maximum Data", "Reached")
            }
            progress_bar_pop.visibility = View.GONE
        })
    }

    private fun showFreshList(dealsModel: DealsModel) {
        popularDealsArrlist = dealsModel.deals?.data!!
        recylerDataAdapter = RecylerDataAdapter(activity!!, popularDealsArrlist)
        recycler_popular.adapter = recylerDataAdapter
    }

    private fun showAppendedList(dealsModel: DealsModel) {
        popularDealsArrlist.addAll(dealsModel.deals?.data!!)
        recylerDataAdapter?.notifyDataSetChanged()
    }

    private var recyclerViewOnScrollListner = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy > 0) {
                visibleItemCount = recyclerView.childCount
                totalItemCount = layoutManager.itemCount
                firstVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()

                if (isLoading) {
                    if ((visibleItemCount!! + firstVisibleItemPosition) >= totalItemCount!!) {
                        isLoading = false
                        pageCount++
                        popularDealsViewModel.getPopularDeals(perPage, pageCount)
                        isLoading = true
                    }
                }
            }
        }
    }

}
