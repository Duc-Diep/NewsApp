package com.ducdiep.newsapp.activities

import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ducdiep.newsapp.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        var intent = intent
        var url = intent.getStringExtra("url")
        try {
            web_view.getSettings().setJavaScriptEnabled(true)
            web_view.loadUrl(url!!)
        } catch (e: Exception) {
            Toast.makeText(this, "Unknow website", Toast.LENGTH_SHORT).show()
        }
        web_view.setWebViewClient(WebViewClient())
    }
}