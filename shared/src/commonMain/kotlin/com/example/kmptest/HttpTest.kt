package com.example.kmptest

import de.jensklingenberg.ktorfit.converter.builtin.CallResponseConverter
import de.jensklingenberg.ktorfit.converter.builtin.FlowResponseConverter
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import de.jensklingenberg.ktorfit.ktorfit
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.logging.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

interface SampleApi {
    @GET("test_person.json")
    suspend fun getPersonAsDTO(): Person

    @GET("test_person.php")
    suspend fun getPersonAsDTO(@Query("age") age: Int): Person

    @GET("test_person.txt")
    suspend fun getPersonByAsText(): String
}


fun testKtorFit() {
    val ktorfit = ktorfit {
        baseUrl("http://www.scurab.com/")
        httpClient(HttpClient {
            install(ContentNegotiation) {
                json(Json { isLenient = true; ignoreUnknownKeys = true })
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("HTTP Client:$message")
                    }
                }
                level = LogLevel.ALL
            }
        })
        responseConverter(FlowResponseConverter(), CallResponseConverter())
    }

    val sampleApi = ktorfit.create<SampleApi>()

    runBlocking(Dispatchers.Default) {
        val client = HttpClient()
        val response = client.get("https://www.pavelcountdown.cz/").bodyAsText()
        println(response)
        //this is freezing on iOS for some reason, might be http related

    //        val personJson = sampleApi.getPersonByAsText()
//        println(personJson)
//        val person1 = sampleApi.getPersonAsDTO()
//        println(person1)
//        val person2 = sampleApi.getPersonAsDTO(123)
//        println(person2)
    }
}