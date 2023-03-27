package com.example.kmptest

import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.mp.KoinPlatformTools

internal class Repository() {

    init {
        instances++
    }

    companion object {
        var instances = 0; private set
    }
}

internal class TestUseCase(val repository: Repository) {
    var counter: Int = 0; private set

    fun inc() {
        counter++
    }
}

fun koinTest() {
    startKoin {
        modules(module {
            single { Repository() }
            factory { TestUseCase(get()) }
        })
    }

    val testUseCase1 = KoinPlatformTools.defaultContext().get().get<TestUseCase>()
    testUseCase1.inc()
    val testUseCase2 = KoinPlatformTools.defaultContext().get().get<TestUseCase>()
    require(testUseCase1 != testUseCase2)
    require(Repository.instances == 1)
    require(testUseCase1.counter == 1)
    require(testUseCase2.counter == 0)
}