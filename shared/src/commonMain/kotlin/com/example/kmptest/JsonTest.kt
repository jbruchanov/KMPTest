package com.example.kmptest

import kotlinx.serialization.SerialName
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@kotlinx.serialization.Serializable
data class Person(
    @SerialName("Name") val name: String,
    @SerialName("Age") val age: Int
)

fun jsonTest() {
    val p1 = Person(name = "Joe Shmoe", age = 22)
    val json = Json.encodeToString(p1)
    val p2 = Json.decodeFromString<Person>(json)
    require(p1 == p2)
}