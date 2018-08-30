package com.dan.happy

import com.fasterxml.jackson.databind.ObjectMapper
import org.http4k.core.Body
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.format.ConfigurableJackson
import com.dan.happy.PPJackson.auto
import com.fasterxml.jackson.annotation.JsonInclude
import org.http4k.format.defaultKotlinModuleWithHttp4kSerialisers
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer
import java.io.BufferedReader
import com.fasterxml.jackson.databind.DeserializationFeature.*
import com.fasterxml.jackson.databind.SerializationFeature

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


object PPJackson: ConfigurableJackson(ObjectMapper()
    .registerModule(defaultKotlinModuleWithHttp4kSerialisers)
    .disableDefaultTyping()
    .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
    .configure(FAIL_ON_IGNORED_PROPERTIES, false)
    .configure(USE_BIG_DECIMAL_FOR_FLOATS, true)
    .configure(USE_BIG_INTEGER_FOR_INTS, true)
    .enable(SerializationFeature.INDENT_OUTPUT)
)


object WebServer {

    fun create(port: Int) = routes(
        "/findmatches" bind Method.GET to ::runMatches
    ).asServer(Jetty(port))

    private fun runMatches(r: Request): Response {
        val data = Any().javaClass.getResourceAsStream("/matches.json").bufferedReader().use(BufferedReader::readText)

        val lens = Body.auto<MatchResult>().toLens()
        val dataJson = lens.extract(Response(Status.OK).body(data))

        return lens.inject(dataJson, Response(Status.OK).header("Content-type", "application/json"))
    }
}
