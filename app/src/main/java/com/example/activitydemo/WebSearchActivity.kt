package com.example.activitydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.activitydemo.databinding.ActivityWebSearchBinding

class WebSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityWebSearchBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        binding.webView.webViewClient = WebViewClient() //prevents opening in browser app
        binding.webView.settings.javaScriptEnabled = true // required for search functionality on Kotlin and Android sites

        val url = savedInstanceState?.getString("url") ?: intent.getStringExtra("url")

        binding.webView.loadUrl(url!!)
    }
}