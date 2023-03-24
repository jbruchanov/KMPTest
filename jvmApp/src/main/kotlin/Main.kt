import com.example.kmptest.testAll
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    //main dispatcher via
    runBlocking {
        testAll()
    }
    println("Ending")
}

