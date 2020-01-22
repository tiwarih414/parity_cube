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
import com.paritycube.paritycube_assignmernt.home.factory.PopularDealsViewModelFactory
import com.paritycube.paritycube_assignmernt.home.model.DealsModel
import com.paritycube.paritycube_assignmernt.home.viewModel.PopularDealsViewModel
import kotlinx.android.synthetic.main.fragment_popular.*

/**
 * A simple [Fragment] subclass.
 */
class PopularFragment : Fragment() {

    lateinit var popularDealsViewModel: PopularDealsViewModel
    lateinit var popularDealsArrlist: ArrayList<DealsModel.Datum>

    lateinit var progressDialog: ProgressDialog
    var recylerDataAdapter: RecylerDataAdapter? = null

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

        progressDialog = ProgressDialog(activity)
        progressDialog.setCancelable(false)
        progressDialog.setMessage(resources.getString(R.string.please_wait))
        progressDialog.show()

        popularDealsArrlist = ArrayList()
        popularDealsViewModel = ViewModelProviders.of(
            activity!!, PopularDealsViewModelFactory(activity!!.application, 10, 1)
        ).get(PopularDealsViewModel::class.java)
        popularDealsViewModel.showError.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

        getPopularDeals()
    }

    private fun getPopularDeals() {
        popularDealsViewModel.getPopularDealsRepository()?.observe(this, Observer {
            if (it != null) {
                popularDealsArrlist.addAll(it.deals?.data!!)
                recylerDataAdapter?.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    activity,
                    resources.getString(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
            }
            setUpRecyclerView(popularDealsArrlist)
        })
    }

    private fun setUpRecyclerView(popularDealsArrList: ArrayList<DealsModel.Datum>) {
        progressDialog.dismiss()
        recylerDataAdapter = RecylerDataAdapter(activity!!, popularDealsArrList)
        recycler_popular.layoutManager = LinearLayoutManager(activity!!)
        recycler_popular.adapter = recylerDataAdapter
        recylerDataAdapter?.notifyDataSetChanged()
    }

}
