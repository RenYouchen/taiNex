package com.kot.tainex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.navigation.Navigation

class Startup : AppCompatActivity() {
    private var delayMili = 2000L;
    //TODO change time
    //private var delayMili = 0L;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.startup)
        Handler(Looper.getMainLooper()).postDelayed({
            val toSignin = Intent(this, MainActivity::class.java)
            startActivity(toSignin)
            finish()
        },delayMili)
    }
}