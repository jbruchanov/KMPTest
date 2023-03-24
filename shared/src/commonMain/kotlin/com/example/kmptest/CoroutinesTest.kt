package com.example.kmptest

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.take


fun coroutinesTest() = runBlocking {

    val flow = MutableStateFlow(-1)

    GlobalScope.launch(Dispatchers.Main) {
        repeat(3) {
            println("Coroutine:[${getThreadName()}]:$it")
            flow.emit(it)
            delay(100)
        }
    }

    GlobalScope.launch {
        flow
            .take(3)
            .collect {
                println("Flow:[${getThreadName()}]:$it")
            }
        println("Leaving flow coroutine")
    }
}