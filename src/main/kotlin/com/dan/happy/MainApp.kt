package com.dan.happy

import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.server.Jetty
import org.http4k.server.asServer
import java.io.BufferedReader

fun main(args: Array<String>) {
    makeServer(9090).start()
}

fun makeServer(port: Int) = ::runMatches.asServer(Jetty(port))

private fun runMatches(r: Request): Response {
    val data = Any().javaClass.getResourceAsStream("/matches.json").bufferedReader().use(BufferedReader::readText)
    return Response(Status.OK).header("Content-type", "application/json").body(data)
}