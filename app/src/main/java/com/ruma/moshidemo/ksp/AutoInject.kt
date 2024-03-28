package com.ruma.moshidemo.ksp

class AutoInject {
    var map = HashMap<String, Class<*>>()
    fun init() {
        map["test1/test"] = Sample1::class.java
        map["test2/test"] = Sample2::class.java
    }

    fun getRouteMap() = map

}