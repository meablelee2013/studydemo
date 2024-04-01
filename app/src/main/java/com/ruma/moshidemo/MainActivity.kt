package com.ruma.moshidemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ruma.moshidemo.http.MyViewModel
import com.ruma.moshidemo.http.login.LoginUtil
import com.ruma.moshidemo.scan.ScanActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        findViewById<Button>(R.id.textBtn).setOnClickListener {

//            lifecycleScope.launch {
////                viewModel.refreshData()
////                viewModel.getDiscoverThemeModelHttpCode()
////                viewModel.getDiscoverTemplate()
//                viewModel.request({ apiService.queryAllThemes() }, {
//                    Log.d("alex", it.data.toString())
//                }, {})
//            }
//            LoginUtil.goLogin(this, Bundle()) {
//                this@MainActivity.startActivity(Intent(this@MainActivity, ScanActivity::class.java))
//            }
            this@MainActivity.startActivity(Intent(this@MainActivity, ScanActivity::class.java))
        }
//
//        viewModel.combineResponse.observe(this) { data ->
//            Log.d("alex", data.allThemeResult.toString())
//        }
//
//        viewModel.discoverThemeModel.observe(this) { data ->
//            Log.d("alex", data.toString())
//        }
    }
}