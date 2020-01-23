package com.paritycube.paritycube_assignmernt.home.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paritycube.paritycube_assignmernt.R
import com.paritycube.paritycube_assignmernt.home.adapter.RecylerDataAdapter
import com.paritycube.paritycube_assignmernt.home.factory.TopDealsViewModelFactory
import com.paritycube.paritycube_assignmernt.home.model.DealsModel
import com.paritycube.paritycube_assignmernt.home.viewModel.TopDealsViewModel
import kotlinx.android.synthetic.main.fragment_top.*

/**
 * A simple [Fragment] subclass.
 */
class TopFragment : Fragment() {
    lateinit var topDealsViewModel: TopDealsViewModel
    lateinit var topDealsArrlist: ArrayList<DealsModel.Datum>

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
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        topDealsArrlist = ArrayList()
        topDealsViewModel = ViewModelProviders.of(
            activity!!, TopDealsViewModelFactory(activity!!.application, perPage, pageCount)
        ).get(TopDealsViewModel::class.java)
        topDealsViewModel.showError.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

        getTopDeals()

        layoutManager = LinearLayoutManager(activity!!)
        recycler_top.layoutManager = layoutManager
        recycler_top.addOnScrollListener(recyclerViewOnScrollListner)
    }

    private fun getTopDeals() {
        topDealsViewModel.getTopDealsRepository()?.observe(this, Observer {
            if (it != null) {
                if (pageCount == 1) {
                    showFreshList(it)
                } else {
                    showAppendedList(it)
                }
            } else {
                Log.d("Maximum Data", "Reached")
            }
            progress_bar.visibility = View.GONE
        })
    }

    private fun showFreshList(dealsModel: DealsModel) {
        topDealsArrlist = dealsModel.deals?.data!!
        recylerDataAdapter = RecylerDataAdapter(activity!!, topDealsArrlist)
        recycler_top.adapter = recylerDataAdapter
    }

    private fun showAppendedList(dealsModel: DealsModel) {
        topDealsArrlist.addAll(dealsModel.deals?.data!!)
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
                        topDealsViewModel.getTopDeals(perPage, pageCount)
                        isLoading = true
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home, menu)

        var searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        var searchView =
            menu.findItem(R.id.action_search)?.actionView as androidx.appcompat.widget.SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
        searchView.maxWidth = Integer.MAX_VALUE

        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                recylerDataAdapter?.filter?.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recylerDataAdapter?.filter?.filter(newText)
                return false
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId

        if (id == R.id.action_search) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
