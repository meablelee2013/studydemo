package com.ruma.moshidemo.http.guide

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserHelper {

    /******************** one way to start coroutine not in activity and fragment ********************/
    private val scope = CoroutineScope(Dispatchers.IO)
    fun performBackgroundTask() {
        // 启动协程
        scope.launch {
            try {
                // 在这里执行耗时的后台任务
                withContext(Dispatchers.IO) {
                    // 模拟数据库操作或网络请求
                    val result = performDatabaseOperation()
                    // 使用 result 更新 UI 或执行其他操作
                }
            } catch (e: Exception) {
                // 处理异常
                e.printStackTrace()
            }
        }
    }

    // 模拟数据库操作或网络请求
    private suspend fun performDatabaseOperation(): String {
        // 这里执行一些耗时操作
        delay(1000) // 模拟延迟
        return "Result from database or network"
    }


    /******************** another way to start coroutine  not in activity and fragment ********************/
    private val parentJob = Job()
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        // Handle any exceptions thrown by coroutines
        println("Coroutine Exception: $exception")
    }

    // Create a CoroutineScope with a context and job
    private val coroutineScope = CoroutineScope(Dispatchers.Default + parentJob + coroutineExceptionHandler)

    fun fetchData() {
        // Launch a coroutine in the CoroutineScope
        coroutineScope.launch {
            // Perform asynchronous operation
            val result = fetchDataFromNetwork()
            println("Received data: $result")
        }
    }

    private suspend fun fetchDataFromNetwork(): String {
        // Simulate a network call delay
        delay(2000)
        return "Sample data from network"
    }

    // Cancel the coroutine when it's no longer needed
    fun cancelCoroutines() {
        parentJob.cancel()
    }
}