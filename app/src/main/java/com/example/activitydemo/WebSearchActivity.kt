package com.example.activitydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activitydemo.databinding.ActivityWebSearchBinding

class WebSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityWebSearchBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
    }
}