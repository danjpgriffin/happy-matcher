package com.dan.happy

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.http4k.format.defaultKotlinModuleWithHttp4kSerialisers

data class MatchResult(val matches: List<Match>)
data class City(val name: String, val lat: Double, val lon: Double)
data class Match(
    val display_name: String,
    val age: Int,
    val job_title: String,
    val height_in_cm: Int,
    val city: City,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val main_photo: String?,
    val compatibility_score: Double,
    val contacts_exchanged: Int,
    val favourite: Boolean,
    val religion: String
)

val mapper: ObjectMapper = ObjectMapper()
    .registerModule(defaultKotlinModuleWithHttp4kSerialisers)
    .disableDefaultTyping()
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
    .configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true)
    .configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, true)
    .enable(SerializationFeature.INDENT_OUTPUT)

fun City.distanceFrom(other: City) = DistanceCalculator.distance(this.lat, this.lon, other.lat, other.lon).toInt()