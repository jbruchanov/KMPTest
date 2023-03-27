package com.example.kmptest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


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