package com.example.kmptest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import nl.adaptivity.xmlutil.serialization.XML
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName


@Serializable
@XmlSerialName("Person", "", "")
data class PersonXml(
    @SerialName("Name") @XmlElement(true) val name: String,
    @XmlElement(false) val age: Int
)

fun xmlTest() {
    val xml = XML {
        // configuration options
        autoPolymorphic = true
    }
    val p1 = PersonXml(name = "Joe Shmoe", age = 22)
    val txt = xml.encodeToString(p1)
    val p2 = xml.decodeFromString<PersonXml>(txt)
    require(p1 == p2)
}