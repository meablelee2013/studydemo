package com.ruma.moshidemo.http.login

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ruma.moshidemo.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 模拟点击登录按钮进行登录
        val loginButton = findViewById<Button>(R.id.login)
        loginButton.setOnClickListener {
            // 模拟登录成功操作
            loginSuccess()
        }
    }

    private fun loginSuccess() {
        // 登录成功后通知 LoginUtil 更新登录状态
        LoginUtil.loginStatusLiveData.value = true
        // 关闭登录界面
        finish()
    }
}
