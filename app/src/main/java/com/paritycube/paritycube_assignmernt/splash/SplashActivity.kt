package com.paritycube.paritycube_assignmernt.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.paritycube.paritycube_assignmernt.R
import com.paritycube.paritycube_assignmernt.home.activity.HomeActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()
    }

    private fun init() {
        Handler().postDelayed(object : Runnable {
            override fun run() {
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                finish()
            }
        }, 1000)
    }
}
