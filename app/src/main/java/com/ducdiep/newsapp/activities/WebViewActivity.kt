package com.ducdiep.newsapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ducdiep.newsapp.R
import com.ducdiep.newsapp.URL
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        var intent = intent
        var url = intent.getStringExtra(URL)
        try {
            var webSetting = web_view.settings
            webSetting.javaScriptEnabled = true
            webSetting.domStorageEnabled = true
            webSetting.databaseEnabled = true
            web_view.loadUrl(url!!)
        } catch (e: Exception) {
            Toast.makeText(this, "Unknow website", Toast.LENGTH_SHORT).show()
        }
        web_view.webViewClient = WebViewClient()
    }

    override fun onBackPressed() {
        if (web_view.canGoBack()) {
            web_view.goBack()
        } else {
            finish()
        }
    }

}