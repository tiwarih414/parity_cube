package com.paritycube.paritycube_assignmernt.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
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
            activity!!, TopDealsViewModelFactory(activity!!.application, 10, 1)
        ).get(TopDealsViewModel::class.java)
        topDealsViewModel.showError.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

        getTopDeals()
    }

    private fun getTopDeals() {
        topDealsViewModel.getTopDealsRepository()?.observe(this, Observer {
            if (it != null) {
                topDealsArrlist.addAll(it.deals?.data!!)
                recylerDataAdapter?.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    activity,
                    resources.getString(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }
            setUpRecyclerView(topDealsArrlist)
        })
    }

    private fun setUpRecyclerView(topDealsArrList: ArrayList<DealsModel.Datum>) {
        progress_bar.visibility = View.GONE
        recylerDataAdapter = RecylerDataAdapter(activity!!, topDealsArrList)
        recycler_top.layoutManager = LinearLayoutManager(activity!!)
        recycler_top.adapter = recylerDataAdapter
        recylerDataAdapter?.notifyDataSetChanged()
    }

}
