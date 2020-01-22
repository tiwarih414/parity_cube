//package com.paritycube.paritycube_assignmernt.home.activity
//
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.preference.PreferenceManager
//import android.util.Log
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.foogoandroidv2.R
//import com.example.foogoandroidv2.V1.adapter.MyOrderAdapter
//import com.example.foogoandroidv2.requestEnvelopes.orderList.*
//import com.example.foogoandroidv2.utils.Helper
//import com.example.foogoandroidv2.utils.USER_DETAILS
//import com.example.foogoandroidv2.utils.WebConfig
//import com.google.gson.Gson
//import kotlinx.android.synthetic.main.activity_my_orders.*
//import kotlinx.android.synthetic.main.toolbar.*
//import org.json.JSONArray
//import org.json.JSONObject
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class MyOrdersActivity : AppCompatActivity() {
//
//    var sharedPreferences: SharedPreferences? = null
//    var customerId = ""
//
//    lateinit var layoutManager: RecyclerView.LayoutManager
//    lateinit var orderHistoryList: ArrayList<OrderHistory>
//    lateinit var myOrderAdapter: MyOrderAdapter
//
//    var pageCount = 0
//    var isLoading = true
//    var visibleItemCount: Int? = null
//    var totalItemCount: Int? = null
//    var firstVisibleItemPosition = 0
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_my_orders)
//
//        initViews()
//    }
//
//    private fun initViews() {
//
//        toolbar_.title = "My Orders"
//        toolbar_.setNavigationOnClickListener {
//            finish()
//        }
//
//        sharedPreferences = Helper.getSharedPreferenceObj(USER_DETAILS)
//        customerId = sharedPreferences?.getString("customerId", null).toString()
//
//        layoutManager = LinearLayoutManager(this)
//        rv_myorders.layoutManager = layoutManager
////        rv_myorders.addOnScrollListener(recyclerViewOnScrollListener)
//
//        progressBar.show()
//        getMyOrders()
//    }
//
//    private fun getMyOrders() {
//        val getOrderList = GetOrderListReq.GetOrderHistory()
//        getOrderList.customerId = customerId
//        getOrderList.lastCount = pageCount
//
//        val reqBody = GetOrderListReq.ReqBody()
//        reqBody.getOrderHistory = getOrderList
//
//        val getOrderListReq = GetOrderListReq()
//        getOrderListReq.body = reqBody
//
//        WebConfig.providesApi()
//            ?.request(getOrderListReq)
//            ?.enqueue(object
//                : Callback<GetOrderListRes> {
//
//                override fun onResponse(
//                    call: Call<GetOrderListRes>,
//                    response: Response<GetOrderListRes>
//                ) {
//                    if (response.code() == 200) {
//                        progressBar.hide()
//                        try {
//                            val res = response.body() as GetOrderListRes
//                            val serverData = Gson().fromJson(
//                                res.body!!.getOrderHistoryResponse!!.getOrderHistoryResult,
//                                ServerData::class.java
//                            )
//                            if (!serverData.webResponse.hasError) {
//                                try {
//                                    serverData.webResponse.resultData.order.orderHistory = ArrayList(Gson().fromJson(
//                                        JSONArray(Gson().toJson(serverData.webResponse.resultData.order.orderHistoryAny)).toString(),
//                                        Array<OrderHistory>::class.java
//                                    ).toList())
//
//                                } catch (e: Exception) {
//                                    serverData.webResponse.resultData.order.orderHistory = ArrayList(Gson().fromJson(
//                                        JSONArray().put(
//                                            JSONObject(
//                                                Gson().toJson(serverData.webResponse.resultData.order.orderHistoryAny)
//                                            )
//                                        ).toString(), Array<OrderHistory>::class.java
//                                    ).toList())
//                                }
//
//                                serverData.webResponse.resultData.order.orderHistory.forEachIndexed { index, orderHistory ->
//                                    try {
//                                        orderHistory.item.orderDetailArray = Gson().fromJson(
//                                            JSONArray(Gson().toJson(orderHistory.item.orderDetails)).toString(),
//                                            Array<OrderDetails>::class.java
//                                        ).toList()
//
//                                    } catch (e: Exception) {
//                                        orderHistory.item.orderDetailArray = Gson().fromJson(
//                                            JSONArray().put(
//                                                JSONObject(
//                                                    Gson().toJson(orderHistory.item.orderDetails)
//                                                )
//                                            ).toString(), Array<OrderDetails>::class.java
//                                        ).toList()
//                                    }
//                                }
// if (pageCount == 0)
//                                     showOrderListFresh(serverData.webResponse)
//                                 else
//                                     showOrderListAppend(serverData.webResponse)
//
//                                showOrderListFresh(serverData.webResponse)
//                            } else
//                                Toast.makeText(
//                                    this@MyOrdersActivity,
//                                    serverData.webResponse.responseMessage,
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                        } catch (e: Exception) {
//                            e.printStackTrace()
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<GetOrderListRes>, t: Throwable) {
//                    progressBar.hide()
////                    Log.d(RestaurantsActivity.TAG, "Himanshu onFailure " + t.message)
////                    t.printStackTrace()
//                }
//
//            })
//    }
//
//    private fun showOrderListFresh(webResponse: WebResponse) {
//        orderHistoryList = webResponse.resultData.order.orderHistory
//        myOrderAdapter = MyOrderAdapter(this, orderHistoryList)
//        rv_myorders.adapter = myOrderAdapter
//    }
//
//    private fun showOrderListAppend(webResponse: WebResponse) {
//        orderHistoryList.addAll(webResponse.resultData.order.orderHistory)
//        myOrderAdapter.notifyDataSetChanged()
//    }
//
//    private var recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {
//        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//            super.onScrolled(recyclerView, dx, dy)
//            if (dy > 0) {
//                visibleItemCount = recyclerView.childCount
//                totalItemCount = layoutManager.itemCount
//                firstVisibleItemPosition =
//                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
//
//                if (isLoading) {
//                    if ((visibleItemCount!! + firstVisibleItemPosition) >= totalItemCount!!) {
//                        isLoading = false
//                        pageCount++
//                        getMyOrders()
//                        isLoading = true
//                    }
//                }
//            }
//        }
//    }
//}
