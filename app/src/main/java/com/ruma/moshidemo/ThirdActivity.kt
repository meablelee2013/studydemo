package com.ruma.moshidemo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ruma.moshidemo.http.MyViewModel
import com.ruma.moshidemo.http.base.apiService
import com.ruma.moshidemo.http.login.LoginUtil
import kotlinx.coroutines.launch

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.third_main)

    }
}