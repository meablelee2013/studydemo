package com.ruma.moshidemo.ksp

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Route(val path: String = "")
