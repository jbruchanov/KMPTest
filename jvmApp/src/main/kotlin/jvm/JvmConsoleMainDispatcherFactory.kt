package jvm

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.internal.MainDispatcherFactory
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

@OptIn(InternalCoroutinesApi::class)
class JvmConsoleMainDispatcherFactory : MainDispatcherFactory {

    /*
    expecting file:
    jvmApp\src\main\resources\META-INF\services\kotlinx.coroutines.internal.MainDispatcherFactory:
    content:
    jvm.JvmConsoleMainDispatcherFactory
     */
    init {
        println("JvmConsoleMainDispatcherFactory")
    }

    private val dispatcher = Executors.newFixedThreadPool(1).asCoroutineDispatcher()
    override val loadPriority: Int
        get() = Int.MAX_VALUE / 2

    override fun createDispatcher(allFactories: List<MainDispatcherFactory>): MainCoroutineDispatcher {
        return object : MainCoroutineDispatcher() {
            override val immediate: MainCoroutineDispatcher
                get() = this

            override fun dispatch(context: CoroutineContext, block: Runnable) {
                dispatcher.dispatch(context, block)
            }
        }
    }
}