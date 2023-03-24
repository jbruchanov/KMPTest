package com.example.kmptest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform


expect fun getThreadName(): String