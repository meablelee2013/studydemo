package com.ruma.moshidemo.http.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.kunminx.architecture.ui.callback.UnPeekLiveData

object LoginUtil {

    var loginStatusLiveData = UnPeekLiveData.Builder<Boolean>().setAllowNullValue(true).create()

    init {
        // 初始化登录状态为未登录
        loginStatusLiveData.value = false
    }

    fun goLogin(activity: AppCompatActivity, bundle: Bundle, onSuccess: (Boolean) -> Unit) {
        // 移除之前的观察者，确保每次调用 goLogin 时只有一个观察者生效
        removePreObserveLoginStatus(activity)
        if (isLoggedIn()) {
            // 如果已经登录，直接回调成功
            onSuccess(true)
        } else {
            observeLoginStatus(activity) { isLogin ->
                Log.d("alex","time = ${System.currentTimeMillis()}")
                onSuccess(isLogin)
            }
            activity.startActivity(Intent(activity, LoginActivity::class.java))
        }
    }


    private fun isLoggedIn(): Boolean {
        // 获取当前登录状态
        return loginStatusLiveData.value ?: false
    }

    private fun observeLoginStatus(owner: LifecycleOwner, observer: Observer<Boolean>) {
        // 观察登录状态变化
        loginStatusLiveData.observe(owner, observer)
    }

    private fun removePreObserveLoginStatus(activity: AppCompatActivity) {
        loginStatusLiveData.removeObservers(activity)
    }
}
