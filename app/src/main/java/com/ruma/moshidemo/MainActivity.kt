package com.ruma.moshidemo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ruma.moshidemo.http.MyViewModel
import com.ruma.moshidemo.http.base.apiService
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        findViewById<Button>(R.id.textBtn).setOnClickListener {

            lifecycleScope.launch {
//                viewModel.refreshData()
//                viewModel.getDiscoverThemeModelHttpCode()
//                viewModel.getDiscoverTemplate()
                viewModel.request({ apiService.queryAllThemes() }, {
                    Log.d("alex", it.data.toString())
                }, {})
            }
        }

        viewModel.combineResponse.observe(this) { data ->
            Log.d("alex", data.allThemeResult.toString())
        }

        viewModel.discoverThemeModel.observe(this) { data ->
            Log.d("alex", data.toString())
        }
    }
}