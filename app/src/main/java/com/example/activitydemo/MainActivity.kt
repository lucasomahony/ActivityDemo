package com.example.activitydemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.activitydemo.databinding.ActivityMainBinding
import java.net.URLEncoder
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val encodedSearchTerm: String
        get() = URLEncoder.encode(binding.etSearch.text.toString(), "UTF-8")

    private var prevUrl: String? =
        null // this is a nullable string property named prevUrl that we instantiate it with a value of null. We need to specify the type as it cannot be inferred

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == Activity.RESULT_OK) {
                val url = result.data?.getStringExtra("url")
                if (url != null) {
                    prevUrl = url
                    binding.btnPrevious.visibility = View.VISIBLE
                } else {
                    binding.btnPrevious.visibility = View.INVISIBLE
                }
            }

        }


    private fun loadWebActivity(url: String) {
        val intent = Intent(this, WebSearchActivity::class.java)
        intent.putExtra("url", url)
        resultLauncher.launch(intent)
    }

    private fun toggleButtonsState(enabled: Boolean){
        binding.btnAndroid.isEnabled = enabled
        binding.btnKotlin.isEnabled = enabled
        binding.btnStackOverflow.isEnabled = enabled
        binding.btnGoogle.isEnabled = enabled
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnGoogle.setOnClickListener {
            loadWebActivity("https://google.co.uk/search?q=$encodedSearchTerm")
        }
        binding.btnStackOverflow.setOnClickListener {
            loadWebActivity("https://stackoverflow.com/search?q=$encodedSearchTerm")
        }
        binding.btnKotlin.setOnClickListener {
            loadWebActivity("https://kotlinlang.org/?q=$encodedSearchTerm&p=0")
        }
        binding.btnAndroid.setOnClickListener {
            loadWebActivity("https://developer.android.com/s/results/?q=$encodedSearchTerm")
        }
        binding.btnPrevious.setOnClickListener {
            loadWebActivity(prevUrl ?: "")
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                toggleButtonsState(binding.etSearch.text.length > 1)
            }
        })

        toggleButtonsState(false)
    }
}