package com.paritycube.paritycube_assignmernt.home.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.paritycube.paritycube_assignmernt.R
import com.paritycube.paritycube_assignmernt.home.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.app_bar_home.*

class HomeActivity : AppCompatActivity() {

    /* API Details

    * header - key: - X-Desidime-Client
    * value :-  68045fd226ab32029c98bf4533bfa98b3c50423094d292d70ca2702e61a9679b
    *
    * top - https://stagingapi.desidime.com/v3/deals/top.json
    * popular - https://stagingapi.desidime.com/v3/deals/popular.json
    * featured - https://stagingapi.desidime.com/v3/deals/featured.json
    *
    * extra parameter
    * per_page = 10
    * page = 1 (page numbers can be 1,2,3,4.... and so on)*/

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        init()
    }

    private fun init() {
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        pager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(pager)
    }
}
