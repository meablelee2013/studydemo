package com.ruma.moshidemo.http.guide

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

class StartCoroutineDemo {

    //启动一个协程：
    fun startCoroutine1() {
        CoroutineScope(Dispatchers.Main).launch {
            // Your code here
        }

    }

    //在后台线程执行任务：
    fun startTaskOnBackground() {
        CoroutineScope(Dispatchers.IO).launch {
            // Perform background task here
        }
    }

    //等待协程执行完毕：
    fun waitCoroutineComplete() {
        val job = CoroutineScope(Dispatchers.IO).launch {
            // Your background task
        }
        CoroutineScope(Dispatchers.IO).launch {
            // Perform background task here
            job.join() // Wait for completion
        }
    }

    //处理异步任务结果：
    fun waitAsyncTask() {
        val deferred = CoroutineScope(Dispatchers.IO).async {
            // Your async task
            "Result"
        }
        CoroutineScope(Dispatchers.IO).launch {
            val result = deferred.await() // Get the result when it's ready
        }

    }

    //并发执行：
    fun concurrentTask() {
        CoroutineScope(Dispatchers.IO).launch {
            val deferred1 = async { /* Task 1 */ }
            val deferred2 = async { /* Task 2 */ }
            val result = awaitAll(deferred1, deferred2)
        }
    }

    //协程上下文切换：
    //使用 withContext 函数在不同的协程上下文中切换执行环境。
    fun switchContext() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.IO) {
                // Perform background task
                "Result"
            }
            // Update UI with result
        }
    }

    //协程取消：
    fun cancelCoroutine() {
        val job = CoroutineScope(Dispatchers.IO).launch {
            // Your code here
        }
        job.cancel() // Cancel the coroutine
    }

    //超时处理：
    fun handleTimeout() {
        CoroutineScope(Dispatchers.Main).launch {

            try {
                withTimeout(5000) {
                    // Your code here
                }
            } catch (e: TimeoutCancellationException) {
                // Handle timeout
            }
        }

    }
}