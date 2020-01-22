package com.paritycube.paritycube_assignmernt.home.fragment


import android.app.ProgressDialog
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
import com.paritycube.paritycube_assignmernt.home.factory.FeaturedDealsViewModelFactory
import com.paritycube.paritycube_assignmernt.home.model.DealsModel
import com.paritycube.paritycube_assignmernt.home.viewModel.FeaturedDealsViewModel
import kotlinx.android.synthetic.main.fragment_featured.*

/**
 * A simple [Fragment] subclass.
 */
class FeaturedFragment : Fragment() {

    lateinit var featuredDealsViewModel: FeaturedDealsViewModel
    lateinit var featuredDealsArrlist: ArrayList<DealsModel.Datum>

    var recylerDataAdapter: RecylerDataAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_featured, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        featuredDealsArrlist = ArrayList()
        featuredDealsViewModel = ViewModelProviders.of(
            activity!!, FeaturedDealsViewModelFactory(activity!!.application, 10, 1)
        ).get(FeaturedDealsViewModel::class.java)
        featuredDealsViewModel.showError.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

        getFeaturedDeals()
    }

    private fun getFeaturedDeals() {
        featuredDealsViewModel.getFeaturedDealsRepository()?.observe(this, Observer {
            if (it != null) {
                featuredDealsArrlist.addAll(it.deals?.data!!)
                recylerDataAdapter?.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    activity,
                    resources.getString(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }
            setUpRecyclerView(featuredDealsArrlist)
        })
    }

    private fun setUpRecyclerView(featuredDealsArrList: ArrayList<DealsModel.Datum>) {
        recylerDataAdapter = RecylerDataAdapter(activity!!, featuredDealsArrList)
        recycler_featured.layoutManager = LinearLayoutManager(activity!!)
        recycler_featured.adapter = recylerDataAdapter
        recylerDataAdapter?.notifyDataSetChanged()
    }

}
