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
import org.http4k.lens.Query
import org.http4k.lens.boolean
import org.http4k.lens.int

object PPJackson: ConfigurableJackson(mapper)

class WebServer(val matchService: MatchService) {

    private val hasPhoto = Query.boolean().optional("hasPhoto")
    private val inContact = Query.boolean().optional("inContact")
    private val distance = Query.int().optional("distance")
    private val lens = Body.auto<MatchResult>().toLens()

    private val usersCity = City("Birmingham", 52.489471, -1.898575)

    fun create(port: Int) = routes(
        "/findmatches" bind Method.GET to ::runMatches
    ).asServer(Jetty(port))

    private fun runMatches(request: Request): Response {
        return matchService.findMatches(Restrictions(
            hasPhoto = hasPhoto(request),
            inContact = inContact(request),
            distance = distance(request)
        ), usersCity).let { matches ->
            lens.inject(MatchResult(matches), Response(Status.OK).header("Content-type", "application/json"))
        }
    }
}
