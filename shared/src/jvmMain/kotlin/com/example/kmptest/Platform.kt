package com.example.kmptest

class AndroidPlatform : Platform {
    override val name: String = "JVM ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getThreadName(): String = Thread.currentThread().name