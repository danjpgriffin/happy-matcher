package com.dan.happy

import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.format.ConfigurableJackson
import com.dan.happy.PPJackson.auto
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.http4k.core.Body

object PPJackson: ConfigurableJackson(mapper)

class WebServer(val matchService: MatchService) {

    fun create(port: Int) = routes(
        "/findmatches" bind Method.GET to ::runMatches
    ).asServer(Jetty(port))

    private fun runMatches(r: Request): Response {
        val matches = matchService.findMatches()
        val lens = Body.auto<MatchResult>().toLens()
        return lens.inject(MatchResult(matches), Response(Status.OK).header("Content-type", "application/json"))
    }
}
