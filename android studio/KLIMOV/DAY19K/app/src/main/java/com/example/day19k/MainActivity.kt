package com.example.day19k

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity()
{
    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        webView = findViewById(R.id.webView)
        webView.webViewClient = MyWebViewClient()
        webView.settings.javaScriptEnabled = true

        webView.loadUrl("https://www.youtube.com/")
    }

    override fun onBackPressed()
    {
        if(webView.canGoBack()) webView.goBack()
        else super.onBackPressed()
    }
}